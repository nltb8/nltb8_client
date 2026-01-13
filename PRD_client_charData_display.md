# PRD: Luồng hiển thị dữ liệu Part từ Server đến Client

## 1. Tổng quan

### 1.1. Mục đích
Tài liệu này mô tả chi tiết luồng hoạt động từ khi server gửi dữ liệu parts (các phần cơ thể nhân vật) đến khi client hiển thị nhân vật lên màn hình.

### 1.2. Phạm vi
- Request/Response giữa Client và Server
- Xử lý dữ liệu chunk/part trên Client
- Render nhân vật sử dụng dữ liệu parts
- Cache và quản lý dữ liệu parts

---

## 2. Kiến trúc tổng quan

### 2.1. Luồng dữ liệu tổng thể

```
┌─────────┐         ┌─────────┐         ┌─────────┐         ┌─────────┐
│ Database│ ──────> │ Server  │ ──────> │ Client  │ ──────> │ Display │
│  (part) │         │ Service │         │ Handler │         │  Screen │
└─────────┘         └─────────┘         └─────────┘         └─────────┘
     │                   │                   │                   │
     │                   │                   │                   │
  Load Parts      updateCharData()    onCharData()      paintChar()
  (GameData)      Serialize Parts    Load Chunks       Render Parts
```

### 2.2. Các thành phần chính

**Server Side:**
- `GameData.parts`: Danh sách parts từ database
- `Service.updateCharData()`: Serialize và gửi parts

**Client Side:**
- `GameService.doRequestDataChar()`: Request dữ liệu từ server
- `MessageHandler.onMessage()`: Nhận và route message
- `ReadMessenge.onCharData()`: Xử lý dữ liệu parts
- `Res.loadChunkPrivate()`: Load chunk data vào memory
- `Chunk.arr`: Hashtable lưu trữ chunks đã load
- `Char.setInfoWearing()`: Set parts cho nhân vật
- `Char.paintChar()`: Render nhân vật

---

## 3. Chi tiết luồng hoạt động

### 3.1. Phase 1: Client Request Data

**File**: `GameService.java:1065-1084`

**Khi nào được gọi:**
- Khi client khởi động game lần đầu
- Khi cần cập nhật dữ liệu parts (version mismatch)
- Khi login vào nhân vật

**Code:**
```java
public void doRequestDataChar(int version, int idChunk) {
    Message msg = this.init((byte) -125);  // CMD_GET_DATA_CHAR
    try {
        msg.writer().writeByte(version);      // Version hiện tại
        msg.writer().writeShort(idChunk);     // ID chunk cần request
        msg.writer().writeByte(mGraphics.zoomLevel);  // Zoom level
        msg.writer().writeByte(TCanvas.ClientType);   // Client type
        this.session.sendMessage(msg);
        msg.cleanup();
    } catch (Exception var5) {
    }
}
```

**Tham số:**
- `version`: Version dữ liệu parts hiện tại của client (để server biết có cần update không)
- `idChunk`: ID chunk cụ thể cần request (hoặc -1 để request tất cả)

---

### 3.2. Phase 2: Server Response

**File**: `Service.java:1594-1618` (theo PRD_updateCharData.md)

**Quá trình:**
1. Server đọc `GameData.parts` từ database
2. Serialize mỗi part thành byte array
3. Gửi về client theo format:
   ```
   [Byte] 0                    // Flag
   [Int]  size                 // Số lượng parts
   [Loop size lần]:
       [Short] part.id         // ID của part
       [Short] data.length     // Độ dài dữ liệu
       [Byte[]] part.data      // Dữ liệu đã serialize
   [Loop size lần]:
       [Short] part.id         // ID của part (lặp lại)
       [Short] part.id         // ID của part (lặp lại - có thể là bug)
   ```

---

### 3.3. Phase 3: Client Receive & Parse

**File**: `MessageHandler.java:56-58`

**Bước 1: Nhận message**
```java
case -125:  // CMD_GET_DATA_CHAR
    GameCanvas.readMessenge.onCharData(msg);
    break;
```

**File**: `ReadMessenge.java:2987-3029`

**Bước 2: Xử lý dữ liệu**
```java
public void onCharData(Message msg) {
    try {
        // Đọc version chunk
        Res.VERSION_CHUNK = msg.reader().readByte();
        
        // Đọc số lượng chunks
        int chunkLen = msg.reader().readInt();
        
        // Vòng lặp 1: Load chunk data
        int i = 0;
        while (i < chunkLen) {
            short id = msg.reader().readShort();      // Chunk ID
            short len = msg.reader().readShort();     // Data length
            byte[] data = new byte[len];
            msg.reader().read(data);                  // Chunk data
            
            // Load chunk vào memory
            Res.loadChunkPrivate(data, id);
            
            // J2ME: Lưu vào RMS để cache
            if (mSystem.isj2me) {
                datatemp = data;
                Res.listGetChunk.remove(String.valueOf(id));
            }
            ++i;
        }
        
        // Load Char class (có thể cần thiết cho việc render)
        Char.load();
        
        // Vòng lặp 2: Set idBig cho chunks
        i = 0;
        while (i < chunkLen) {
            short id = msg.reader().readShort();
            short idBig = msg.reader().readShort();   // ID hình ảnh lớn
            
            Chunk chunk = (Chunk)Chunk.arr.get(String.valueOf(id));
            if (idBig > -1) {
                chunk.idBig = idBig;
                
                // J2ME: Lưu chunk với idBig vào RMS
                if (mSystem.isj2me && chunkLen == 1) {
                    ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    DataOutputStream d = new DataOutputStream(bo);
                    d.writeShort(idBig);
                    d.write(datatemp);
                    Rms.saveRMS("ck" + id, bo.toByteArray());
                    bo.close();
                }
            }
            ++i;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

**Kết quả:**
- Chunks được load vào `Chunk.arr` (Hashtable)
- Mỗi chunk chứa:
  - `type`: Loại part (0=head, 1=body, 2=leg, 3=weapon)
  - `idBig`: ID hình ảnh lớn
  - `listIDChunkPaint[]`: Danh sách ID chunk để vẽ cho 52 frames
  - `chunkData[]`: Dữ liệu offset (dx, dy) cho mỗi frame
  - `allSmallImg[]`: Danh sách hình ảnh nhỏ

---

### 3.4. Phase 4: Load Chunk Data vào Memory

**File**: `Res.java:248-292`

**Quá trình deserialize:**
```java
public static void loadChunkPrivate(byte[] data, int chunkID) {
    if (chunkID != -1) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        Chunk chunk = new Chunk();
        
        // Đọc thông tin cơ bản
        chunk.type = dis.readByte();           // Loại part
        chunk.idBig = dis.readShort();         // ID hình ảnh lớn
        
        // Đọc danh sách ID chunk paint cho 52 frames
        for(int j = 0; j < 52; ++j) {
            chunk.listIDChunkPaint[j] = dis.readShort();
        }
        
        // Đọc chunk data (offset dx, dy cho mỗi frame)
        chunk.chunkData = new ChunkData[dis.readByte()];
        for(int j = 0; j < chunk.chunkData.length; ++j) {
            chunk.chunkData[j] = new ChunkData();
            chunk.chunkData[j].index = dis.readByte();  // Frame index
            chunk.chunkData[j].dx = dis.readByte();     // Offset X
            chunk.chunkData[j].dy = dis.readByte();     // Offset Y
        }
        
        // Đọc danh sách hình ảnh nhỏ
        int allsmallimg = dis.readByte();
        chunk.allSmallImg = new SmallImage[allsmallimg];
        for(int j = 0; j < allsmallimg; ++j) {
            int id = dis.readUnsignedByte();
            chunk.allSmallImg[id] = new SmallImage(
                id,
                dis.readUnsignedByte(),  // width
                dis.readUnsignedByte(),  // height
                dis.readUnsignedByte(),  // x
                dis.readUnsignedByte()   // y
            );
        }
        
        // Lưu vào hashtable
        Chunk.arr.put(String.valueOf(chunkID), chunk);
    }
}
```

**Cấu trúc Chunk:**
```java
class Chunk {
    short idBig;                    // ID hình ảnh lớn
    byte type;                      // 0=head, 1=body, 2=leg, 3=weapon
    short[] listIDChunkPaint;       // [52] ID chunk cho mỗi frame
    ChunkData[] chunkData;          // Offset dx, dy cho frames
    SmallImage[] allSmallImg;       // Danh sách hình ảnh nhỏ
}
```

---

### 3.5. Phase 5: Set Parts cho Nhân vật

**File**: `Char.java:2253-2271`

**Khi nào được gọi:**
- Khi nhận thông tin nhân vật từ server (`onCharWearing`, `onMainCharInfo`, `onCharInfo`)
- Khi thay đổi trang bị
- Khi hiển thị nhân vật trong shop

**Code:**
```java
public void setInfoWearing(short[] listPart) {
    this.partPaint.removeAllElements();
    if (this.partPaint.size() == 0) {
        this.myListPart = listPart;  // Lưu danh sách part IDs
        
        // Bỏ qua nếu không phải main char và không cần render
        if (!this.isMainChar() && !this.isNPC() && !paintOrtherChar) {
            return;
        }
        
        // Tạo Chunk objects từ part IDs
        Chunk ph = Chunk.getHead(listPart[HEAD], this.getGender());
        Chunk pb = Chunk.getBody(listPart[BODY], this.getGender());
        Chunk pl = Chunk.getLeg(listPart[LEG], this.getGender());
        Chunk pw = Chunk.getWeapon(listPart[WEAPON]);
        
        // Lưu vào partPaint vector để render
        this.partPaint.addElement(ph);  // Index 0: Head
        this.partPaint.addElement(pb);  // Index 1: Body
        this.partPaint.addElement(pl);  // Index 2: Leg
        this.partPaint.addElement(pw);  // Index 3: Weapon
    }
}
```

**Tham số `listPart`:**
- `listPart[0]`: ID part đầu (HEAD)
- `listPart[1]`: ID part thân (BODY)
- `listPart[2]`: ID part chân (LEG)
- `listPart[3]`: ID part vũ khí (WEAPON)

**Lưu ý:**
- `Chunk.getHead()`, `getBody()`, `getLeg()`, `getWeapon()` sẽ tự động load chunk nếu chưa có trong `Chunk.arr`
- Nếu chunk không tồn tại, sẽ fallback về chunk mặc định theo gender

---

### 3.6. Phase 6: Load Chunk từ Cache hoặc Request

**File**: `Chunk.java:27-109`

**Quá trình load chunk:**
```java
public static Chunk getHead(int chunkId, int genderId) {
    try {
        // Kiểm tra chunk đã load chưa
        if (arr.get(String.valueOf(chunkId)) == null) {
            // Chưa có - load từ RMS hoặc request từ server
            Res.loadChunkPrivate(chunkId);
        }
        return (Chunk)arr.get(String.valueOf(chunkId));
    } catch (Exception var3) {
        // Fallback về chunk mặc định
        if (genderId == 0) {  // Nam
            if (arr.get("4") == null) {
                Res.loadChunkPrivate(4);
            }
            return (Chunk)arr.get("4");
        } else {  // Nữ
            if (arr.get("0") == null) {
                Res.loadChunkPrivate(0);
            }
            return (Chunk)arr.get("0");
        }
    }
}
```

**Load từ RMS (cache):**
**File**: `Res.java:294-352`
```java
public static boolean loadChunkPrivateRms(int chunkID) {
    try {
        byte[] data = Rms.loadRMS("ck" + chunkID);
        if (data != null) {
            // Deserialize từ RMS
            // ... (tương tự loadChunkPrivate)
            Chunk.arr.put(String.valueOf(chunkID), chunk);
            return true;
        }
    } catch (Exception e) {
    }
    return false;
}
```

**Load từ file local (fallback):**
**File**: `Res.java:354-390`
```java
public static void loadChunkPrivate(int chunkID) {
    if (chunkID != -1) {
        // Thử load từ RMS trước
        if (!loadChunkPrivateRms(chunkID)) {
            // Không có trong RMS - load từ file local
            DataInputStream dis = new DataInputStream(
                mSystem.getResourceAsStream("/datahd/chunkdata/chunk" + chunkID)
            );
            // ... deserialize và lưu vào Chunk.arr
        }
    }
}
```

---

### 3.7. Phase 7: Render Nhân vật

**File**: `Char.java:816-837`

**Quá trình render:**
```java
public void paintChar(mGraphics g, int x, int y) {
    int idPartHair = this.PartHead;
    int gender = this.getGender();
    mVector vtPartTem = new mVector();
    
    // Thêm head chunk
    vtPartTem.addElement(Chunk.getHead(idPartHair, gender));
    
    // Thêm các parts từ partPaint
    int size = this.partPaint.size();
    if (size > 0) {
        vtPartTem.addElement(this.partPaint.elementAt(1));  // Body
        vtPartTem.addElement(this.partPaint.elementAt(2));  // Leg
        if (size > 3) {
            vtPartTem.addElement(this.partPaint.elementAt(3));  // Weapon
        }
    }
    
    // Render nhân vật với danh sách chunks
    this.paintCharByChunk(g, x, y - this.yFly - this._jum + this.dyWater,
        vtPartTem.size() == 0 ? partPaintDefault[this.getGender()] : vtPartTem,
        this.dir, this.frame, false);
    
    // Vẽ shadow
    Image shadow = this.getShadow();
    if (shadow != null) {
        int ysd = y - 2;
        g.drawImage(shadow, x, ysd, 3, false);
    }
}
```

**File**: `Char.java:1200-1300` (paintCharByChunk)

**Chi tiết render từng part:**
```java
// Vòng lặp render từng part theo thứ tự
for (int i = 0; i < ORDER_PAINT[frame].length; i++) {
    Chunk chunk = null;
    
    // Lấy chunk tương ứng với frame và part type
    if (ORDER_PAINT[frame][i] == HEAD) {
        chunk = (Chunk)vtPart.elementAt(0);
    } else if (ORDER_PAINT[frame][i] == BODY) {
        chunk = (Chunk)vtPart.elementAt(1);
    } else if (ORDER_PAINT[frame][i] == LEG) {
        chunk = (Chunk)vtPart.elementAt(2);
    } else if (ORDER_PAINT[frame][i] == WEAPON) {
        chunk = (Chunk)vtPart.elementAt(3);
    }
    
    // Nếu chunk null, load chunk mặc định
    if (chunk == null) {
        if (ORDER_PAINT[frame][i] == HEAD) {
            chunk = Chunk.getHead(this.myListPart[HEAD], this.getGender());
            this.partPaint.setElementAt(chunk, 0);
        }
        // ... tương tự cho BODY, LEG, WEAPON
        continue;
    }
    
    // Lấy hình ảnh lớn từ idBig
    ImageIcon imgPaint = GameData.getImgIcon((short)(chunk.getBigId() + Res.ID_CHAR));
    
    // Nếu không có hình ảnh, dùng chunk mặc định
    if (imgPaint == null || imgPaint.img == null) {
        chunk = defaultChunk[this.getGender()][ORDER_PAINT[frame][i]];
    }
    
    // Tính toán offset dx, dy cho frame hiện tại
    int dx = 0, dy = 0;
    for (int j = 0; j < chunk.chunkData.length; ++j) {
        if (chunk.chunkData[j].index == frame) {
            dx = chunk.chunkData[j].dx;
            dy = chunk.chunkData[j].dy;
            break;
        }
    }
    
    // Cộng thêm offset từ template
    dx = dx + chunk.getTemplate()[frame].dx;
    short rotale = chunk.getTemplate()[frame].rotate;
    
    // Lấy SmallImage tương ứng với frame
    SmallImage sm = chunk.allSmallImg[chunk.getIcon(frame)];
    
    // Vẽ hình ảnh
    g.drawRegion(
        imgPaint.img,
        sm.x, sm.y,           // Vị trí trong hình ảnh lớn
        sm.w, sm.h,          // Kích thước
        rotale,               // Góc xoay
        x + dx, y + dy,      // Vị trí trên màn hình
        0, false
    );
}
```

**Thứ tự render (ORDER_PAINT):**
- Frame 0 (Stand): HEAD → BODY → LEG → WEAPON
- Frame 1-5 (Walk): HEAD → BODY → LEG → WEAPON
- Frame 6-11 (Attack): HEAD → BODY → LEG → WEAPON
- ... (52 frames tổng cộng)

---

## 4. Cấu trúc dữ liệu

### 4.1. Part (Server)

```java
class Part {
    int id;                    // ID của part
    short idBig;              // ID hình ảnh lớn
    byte type;                 // 0=head, 1=body, 2=leg, 3=weapon
    short[] listIDChunkPaint; // [52] ID chunk cho mỗi frame
    ChunkData[] chunkData;     // Offset dx, dy cho frames
    SmallImage[] allSmallImg;  // Danh sách hình ảnh nhỏ
    byte[] data;               // Dữ liệu đã serialize
}
```

### 4.2. Chunk (Client)

```java
class Chunk {
    short idBig;                    // ID hình ảnh lớn
    byte type;                      // 0=head, 1=body, 2=leg, 3=weapon
    short[] listIDChunkPaint;       // [52] ID chunk cho mỗi frame
    ChunkData[] chunkData;          // Offset dx, dy cho frames
    SmallImage[] allSmallImg;       // Danh sách hình ảnh nhỏ
}
```

### 4.3. ChunkData

```java
class ChunkData {
    byte index;  // Frame index
    byte dx;     // Offset X
    byte dy;     // Offset Y
}
```

### 4.4. SmallImage

```java
class SmallImage {
    int id;      // ID
    int w;       // Width
    int h;       // Height
    int x;       // X position in big image
    int y;       // Y position in big image
}
```

---

## 5. Cache và Performance

### 5.1. Cache Strategy

**Client:**
1. **Memory Cache**: `Chunk.arr` (Hashtable) - lưu chunks đã load
2. **RMS Cache**: Lưu chunks vào RMS với key `"ck" + chunkID`
3. **File Cache**: Load từ `/datahd/chunkdata/chunk{id}` nếu không có trong RMS

**Priority:**
1. Memory (`Chunk.arr`)
2. RMS (`Rms.loadRMS("ck" + chunkID)`)
3. File local (`/datahd/chunkdata/chunk{id}`)
4. Request từ server (nếu không có)

### 5.2. Optimization

**Lazy Loading:**
- Chunks chỉ được load khi cần thiết (khi `setInfoWearing()` được gọi)
- Nếu chunk chưa có, tự động load từ cache hoặc request

**Batch Loading:**
- Server gửi nhiều chunks trong một message
- Client load tất cả chunks cùng lúc trong `onCharData()`

**Version Control:**
- Client gửi version hiện tại khi request
- Server chỉ gửi chunks đã thay đổi (nếu implement)

---

## 6. Error Handling

### 6.1. Chunk không tồn tại

**Fallback Strategy:**
```java
// Trong Chunk.getHead(), getBody(), getLeg()
catch (Exception e) {
    // Fallback về chunk mặc định theo gender
    if (genderId == 0) {
        return Chunk.arr.get("4");  // Chunk mặc định nam
    } else {
        return Chunk.arr.get("0");  // Chunk mặc định nữ
    }
}
```

### 6.2. Hình ảnh không tồn tại

**Fallback Strategy:**
```java
// Trong paintCharByChunk()
ImageIcon imgPaint = GameData.getImgIcon((short)(chunk.getBigId() + Res.ID_CHAR));
if (imgPaint == null || imgPaint.img == null) {
    chunk = defaultChunk[this.getGender()][ORDER_PAINT[frame][i]];
}
```

### 6.3. Data corruption

- Client catch exception và log lỗi
- Fallback về chunk/hình ảnh mặc định
- Không crash game

---

## 7. Test Cases

### 7.1. Test Case 1: Load parts thành công
**Input**: 
- Server gửi parts data hợp lệ
- Chunks chưa có trong cache

**Expected**:
- Chunks được load vào `Chunk.arr`
- Nhân vật render đúng với parts đã set

### 7.2. Test Case 2: Load từ cache
**Input**:
- Chunks đã có trong RMS cache

**Expected**:
- Load từ RMS thay vì request server
- Render nhanh hơn

### 7.3. Test Case 3: Chunk không tồn tại
**Input**:
- Part ID không tồn tại trong `Chunk.arr`

**Expected**:
- Fallback về chunk mặc định
- Nhân vật vẫn render được (với parts mặc định)

### 7.4. Test Case 4: Hình ảnh không tồn tại
**Input**:
- `idBig` không có hình ảnh tương ứng

**Expected**:
- Fallback về hình ảnh mặc định
- Nhân vật vẫn render được

### 7.5. Test Case 5: Thay đổi trang bị
**Input**:
- Nhân vật thay đổi vũ khí/áo/quần

**Expected**:
- `setInfoWearing()` được gọi với listPart mới
- Parts được update trong `partPaint`
- Nhân vật render với parts mới

---

## 8. Tài liệu tham khảo

### 8.1. Files liên quan

**Server:**
- `Service.java`: `updateCharData()` - Gửi parts data
- `GameData.java`: Load parts từ database

**Client:**
- `GameService.java`: `doRequestDataChar()` - Request data
- `MessageHandler.java`: Route message -125
- `ReadMessenge.java`: `onCharData()` - Xử lý data
- `Res.java`: `loadChunkPrivate()` - Load chunk
- `Chunk.java`: Quản lý chunks
- `Char.java`: `setInfoWearing()`, `paintChar()` - Render

### 8.2. Database Schema

**Bảng `part`:**
```sql
CREATE TABLE part (
    id INT PRIMARY KEY,
    idBig SMALLINT,
    type TINYINT,
    listIDChunk TEXT,      -- JSON array [52]
    chunkData TEXT,        -- JSON array
    allSmallImg TEXT       -- JSON array
);
```

---

## 9. Kết luận

Luồng hiển thị dữ liệu parts từ server đến client bao gồm:

1. **Request**: Client gửi request với version và idChunk
2. **Response**: Server serialize và gửi parts data
3. **Parse**: Client deserialize và load chunks vào memory
4. **Cache**: Chunks được cache trong memory và RMS
5. **Set**: Nhân vật set parts thông qua `setInfoWearing()`
6. **Render**: Render từng part theo thứ tự và frame

**Điểm mạnh:**
- Hệ thống cache hiệu quả (Memory → RMS → File → Server)
- Lazy loading - chỉ load khi cần
- Fallback mechanism - đảm bảo nhân vật luôn render được
- Batch loading - tối ưu network

**Điểm cần cải thiện:**
- Version control chưa được implement đầy đủ
- Không có compression cho dữ liệu lớn
- Error logging chưa đầy đủ
