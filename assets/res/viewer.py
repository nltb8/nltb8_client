# python
import pygame, struct, sys, os

TILE_SIZE = 16
SCREEN_SCALE = 2  # scale up for visibility

def load_map(path):
    with open(path, "rb") as f:
        data = f.read()
    # header: w (1 byte), h (1 byte), idTile (1 byte)
    w, h, tile_id = struct.unpack_from("BBB", data, 0)
    offset = 3
    tile_count = w * h
    tiles = list(data[offset:offset + tile_count])
    # normalize 254 -> 255, signed byte handling
    tiles = [255 if t == 254 else t for t in tiles]
    return w, h, tile_id, tiles

def main(map_id):
    pygame.init()
    map_path = os.path.join("datahd", "map", f"m{map_id}")
    w, h, tile_id, tiles = load_map(map_path)

    # load tile atlas; replace with your real path per tile_id
    atlas = pygame.image.load(os.path.join("x1", f"tile{tile_id}.png")).convert_alpha()

    screen = pygame.display.set_mode((w * TILE_SIZE * SCREEN_SCALE, h * TILE_SIZE * SCREEN_SCALE))
    clock = pygame.time.Clock()

    def draw_map():
        for y in range(h):
            for x in range(w):
                t = tiles[y * w + x]
                if t == 255:
                    continue
                src_x = (t % 16) * TILE_SIZE
                src_y = (t // 16) * TILE_SIZE
                tile = atlas.subsurface((src_x, src_y, TILE_SIZE, TILE_SIZE))
                tile = pygame.transform.scale(tile, (TILE_SIZE * SCREEN_SCALE, TILE_SIZE * SCREEN_SCALE))
                screen.blit(tile, (x * TILE_SIZE * SCREEN_SCALE, y * TILE_SIZE * SCREEN_SCALE))

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False
        screen.fill((0, 0, 0))
        draw_map()
        pygame.display.flip()
        clock.tick(60)

    pygame.quit()

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python viewer.py <map_id>")
        sys.exit(1)
    main(int(sys.argv[1]))
