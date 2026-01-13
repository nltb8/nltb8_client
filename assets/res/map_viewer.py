# map_viewer.py
# Usage:
#   python map_viewer.py --root /path/to/assets --level 7
#   python map_viewer.py --root . --level 7 --scale 2
#
# Expected files (relative to --root):
#   datahd/map/m{level}
#   x1/tile{idTile}.type
#   tile{idTile}.png

import argparse
import os
import struct
import sys

# --------- Binary reader (DataInputStream-like) ----------
class BinReader:
    def __init__(self, data: bytes):
        self.data = data
        self.pos = 0

    def remaining(self) -> int:
        return len(self.data) - self.pos

    def read_u8(self) -> int:
        if self.pos >= len(self.data):
            return -1  # mimic DataInputStream.read() EOF
        b = self.data[self.pos]
        self.pos += 1
        return b  # 0..255

    def read_i8(self) -> int:
        v = self.read_u8()
        if v < 0:
            return v
        return v - 256 if v > 127 else v

    def read_u16(self) -> int:
        if self.pos + 2 > len(self.data):
            raise EOFError("Unexpected EOF while reading u16")
        v = struct.unpack_from(">H", self.data, self.pos)[0]
        self.pos += 2
        return v

    def read_i16(self) -> int:
        if self.pos + 2 > len(self.data):
            raise EOFError("Unexpected EOF while reading i16")
        v = struct.unpack_from(">h", self.data, self.pos)[0]
        self.pos += 2
        return v

    def read_bytes(self, n: int) -> bytes:
        if self.pos + n > len(self.data):
            raise EOFError(f"Unexpected EOF while reading {n} bytes")
        out = self.data[self.pos:self.pos+n]
        self.pos += n
        return out


# --------- Map parser (mirrors Tilemap.loadMap old logic) ----------
def load_type_of_tile(root: str, tile_id: int) -> list[int]:
    path = os.path.join(root, "x1", f"tile{tile_id}.type")
    with open(path, "rb") as f:
        data = f.read()
    return [b for b in data]  # Java: new int[is.available()] then is.read()

def load_map_file(root: str, level: int) -> bytes:
    path = os.path.join(root, "datahd", "map", f"m{level}")
    with open(path, "rb") as f:
        return f.read()

def parse_map(root: str, level: int):
    data = load_map_file(root, level)
    br = BinReader(data)

    w = br.read_u8()
    h = br.read_u8()
    tile_id = br.read_u8()

    if w <= 0 or h <= 0 or tile_id < 0:
        raise ValueError(f"Bad header: w={w}, h={h}, tile_id={tile_id}")

    type_of_tile = load_type_of_tile(root, tile_id)

    # base map: short[] map in Java, but filled by (short)isdata.read()
    base = [0] * (w * h)
    tile_type = [0] * (w * h)

    for i in range(w * h):
        v = br.read_u8()
        if v == 254:
            v = 255
        base[i] = v
        if v != 255 and v != -1 and v < len(type_of_tile):
            tile_type[i] = type_of_tile[v]

    # tileTop: key = y*w + x, value = tileIndex
    tile_top = {}

    # label307 while loop
    while True:
        x = br.read_u8()
        if x == -1:
            break

        if x == 255:
            # inner while: read tileTop entries until next 255
            while True:
                x2 = br.read_u8()
                if x2 == -1:
                    # EOF
                    break
                if x2 == 255:
                    # parse extra sections (trees + treeTopBottom + tileLayer3 + treeLow + paintLayer + TreeInfoNew)
                    consume_extra_sections(br)
                    break
                y2 = br.read_u8()
                v2 = br.read_u8()
                if v2 != 255:
                    tile_top[y2 * w + x2] = v2
            break  # break label307 after processing extra sections
        else:
            y = br.read_u8()
            v = br.read_u8()
            if v != 255:
                tile_top[y * w + x] = v

    # After try/catch in Java: read rectangle overlay (y, width, height) and fill tileTop
    # Java order:
    #   y = read(); totalTreeTopBottom = read(); totalTile3 = read();
    #   for i=y..y+totalTile3-1:
    #     for id=x..x+totalTreeTopBottom-1:
    #       dx = (short)read(); if dx!=255 -> tileTop put
    #
    # Note: It uses `x` from the loop scope (the last x read in label307 loop).
    # In practice this rectangle seems to exist after the first tileTop parsing.
    # We replicate exactly: use last known x if available; else assume 0.
    rect_x_start = 0
    # If loop ended by x == 255 branch, rect_x_start is that sentinel (255) not useful.
    # In original code, x is still in scope; but rectangle uses `x` from earlier.
    # We'll be defensive: clamp to 0.
    try:
        y0 = br.read_u8()
        rw = br.read_u8()
        rh = br.read_u8()
        if y0 != -1 and rw != -1 and rh != -1 and rw > 0 and rh > 0:
            for yy in range(y0, y0 + rh):
                for xx in range(rect_x_start, rect_x_start + rw):
                    dx = br.read_u8()
                    if dx != 255:
                        tile_top[yy * w + xx] = dx
    except Exception:
        # Some maps may not have this tail block; ignore safely.
        pass

    return {
        "w": w,
        "h": h,
        "tile_id": tile_id,
        "base": base,
        "tile_top": tile_top,
        "tile_type": tile_type,
    }

def consume_tree_list(br: BinReader, count: int):
    # Java Tree: new Tree(totalTile3, i, value, flag)
    # read: byte, byte, short
    for _ in range(count):
        _a = br.read_u8()
        _b = br.read_u8()
        _c = br.read_i16()

def consume_extra_sections(br: BinReader):
    # Mirrors the order seen in Tilemap.loadMap:
    # ntree (short) + list -> trees
    # ntree (short) + list -> trees2
    # ntree (short) + list -> trees3
    # ntree (short) + list -> trees11
    # ntree (short) + list -> trees12
    # totalTreeTopBottom (u8) + each TreeTopBottom:
    #   xGrid(u8), yGrid(u8), id0(u8), id0*(byte,byte,short), id1(u8), id1*(byte,byte,short)
    # tileLayer3: try { totalTile3(u8); repeat: id(u8), j(u8), value(u8) }
    # treeLow1: size(short) then size*short
    # treeLow2: size(short) then size*short
    # treeLow3: size(short) then size*short
    # paintLayerTree23: byte
    # Tree.ALL_TREE_INFO: size(short) then size*(short + short + short)

    ntree = br.read_i16()
    consume_tree_list(br, ntree)

    ntree = br.read_i16()
    consume_tree_list(br, ntree)

    ntree = br.read_i16()
    consume_tree_list(br, ntree)

    ntree = br.read_i16()
    consume_tree_list(br, ntree)

    ntree = br.read_i16()
    consume_tree_list(br, ntree)

    # TreeTopBottom blocks
    ttb_count = br.read_u8()
    if ttb_count != -1:
        for _ in range(ttb_count):
            _xg = br.read_u8()
            _yg = br.read_u8()

            id0 = br.read_u8()
            for __ in range(id0):
                br.read_u8()
                br.read_u8()
                br.read_i16()

            id1 = br.read_u8()
            for __ in range(id1):
                br.read_u8()
                br.read_u8()
                br.read_i16()

    # tileLayer3 block (wrapped in try in Java)
    # We'll be defensive: only parse if bytes remain.
    if br.remaining() > 0:
        try:
            n = br.read_u8()
            if n != -1:
                for _ in range(n):
                    br.read_u8()  # id
                    br.read_u8()  # j
                    br.read_u8()  # value
        except Exception:
            pass

    # treeLow1/2/3 sets
    for _ in range(3):
        size = br.read_i16()
        for __ in range(size):
            br.read_i16()

    # paintLayerTree23
    br.read_i8()

    # TreeInfoNew list: size(short) then id(short), dx(short), dy(short)
    size = br.read_i16()
    for _ in range(size):
        br.read_i16()  # id (later + Res.ID_ITEM_MAP)
        br.read_i16()  # dx
        br.read_i16()  # dy


# --------- Pygame renderer ----------
def run_pygame(map_obj, root: str, scale: int = 2, fps: int = 60):
    import pygame

    w, h = map_obj["w"], map_obj["h"]
    tile_id = map_obj["tile_id"]
    base = map_obj["base"]
    tile_top = map_obj["tile_top"]

    tile_path = os.path.join(root, "x1", f"tile{tile_id}.png")
    if not os.path.exists(tile_path):
        raise FileNotFoundError(f"Missing tileset image: {tile_path}")

    pygame.init()
    TILE = 16
    screen_w = w * TILE * scale
    screen_h = h * TILE * scale

    screen = pygame.display.set_mode((screen_w, screen_h))
    pygame.display.set_caption(f"Map {w}x{h} | level={args.level} | tile={tile_id}")

    tileset = pygame.image.load(tile_path).convert_alpha()
    ts_w, ts_h = tileset.get_width(), tileset.get_height()
    cols = ts_w // TILE

    # Pre-cut tiles for speed
    tile_surfs = {}
    def get_tile(idx: int):
        if idx in tile_surfs:
            return tile_surfs[idx]
        if idx == 255:
            tile_surfs[idx] = None
            return None
        sx = (idx % cols) * TILE
        sy = (idx // cols) * TILE
        if sy + TILE > ts_h:
            tile_surfs[idx] = None
            return None
        surf = pygame.Surface((TILE, TILE), pygame.SRCALPHA)
        surf.blit(tileset, (0, 0), (sx, sy, TILE, TILE))
        if scale != 1:
            surf = pygame.transform.scale(surf, (TILE * scale, TILE * scale))
        tile_surfs[idx] = surf
        return surf

    clock = pygame.time.Clock()
    running = True
    show_top = True

    # Render once to a cached surface
    world = pygame.Surface((w * TILE * scale, h * TILE * scale), pygame.SRCALPHA)

    def redraw():
        world.fill((0, 0, 0, 255))
        # base
        for yy in range(h):
            for xx in range(w):
                idx = base[yy * w + xx]
                t = get_tile(idx)
                if t:
                    world.blit(t, (xx * TILE * scale, yy * TILE * scale))
        # tileTop overlay
        if show_top:
            for k, idx in tile_top.items():
                yy = k // w
                xx = k % w
                t = get_tile(idx)
                if t:
                    world.blit(t, (xx * TILE * scale, yy * TILE * scale))

    redraw()

    while running:
        for e in pygame.event.get():
            if e.type == pygame.QUIT:
                running = False
            elif e.type == pygame.KEYDOWN:
                if e.key == pygame.K_ESCAPE:
                    running = False
                elif e.key == pygame.K_t:
                    show_top = not show_top
                    redraw()

        screen.blit(world, (0, 0))
        pygame.display.flip()
        clock.tick(fps)

    pygame.quit()


# --------- CLI ----------
if __name__ == "__main__":
    ap = argparse.ArgumentParser()
    ap.add_argument("--root", required=True, help="Asset root folder")
    ap.add_argument("--level", type=int, required=True, help="Map level id (m{level})")
    ap.add_argument("--scale", type=int, default=2, help="Render scale (default 2)")
    ap.add_argument("--fps", type=int, default=60, help="FPS (default 60)")
    args = ap.parse_args()

    try:
        m = parse_map(args.root, args.level)
        print(f"Loaded map level={args.level}: {m['w']}x{m['h']}, tile_id={m['tile_id']}, "
              f"tileTop={len(m['tile_top'])}")
        run_pygame(m, args.root, scale=args.scale, fps=args.fps)
    except Exception as ex:
        print("ERROR:", ex)
        sys.exit(1)
