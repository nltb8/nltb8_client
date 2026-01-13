# PRD: Phương thức updateCharData()

## 1. Tổng quan

### 1.1. Mục đích
Phương thức `updateCharData()` có nhiệm vụ gửi dữ liệu về các phần cơ thể nhân vật (Parts) từ server đến client để client có thể hiển thị và render nhân vật một cách chính xác.

### 1.2. Vị trí trong hệ thống
- **Class**: `com.nltb.network.Service`
- **Package**: `com.nltb.network`
- **Dòng code**: 1594-1618
- **Override từ**: `AbsService.updateCharData()`

### 1.3. Command Protocol
- **CMD**: `CMD.CMD_GET_DATA_CHAR` (giá trị: -125)
- **Loại**: Server-to-Client message
- **Protocol**: Binary data stream

---

## 2. Phân tích chức năng

### 2.1. Luồng xử lý

```
Client Request → Server Response
     ↓                ↓
CMD_GET_DATA_CHAR → updateCharData()
                        ↓
                  Đọc GameData.parts
                        ↓
                  Serialize dữ liệu
                        ↓
                  Gửi về client
```

### 2.2. Cấu trúc dữ liệu Part

Mỗi `Part` chứa thông tin về một phần cơ thể nhân vật:

```java
class Part {
    int id;                    // ID của part
    short idBig;               // ID lớn (big image)
    byte type;                 // Loại part
    short[] listIDChunkPaint;  // Danh sách ID chunk để vẽ
    ChunkData[] chunkData;     // Dữ liệu chunk
    SmallImage[] allSmallImg;  // Danh sách hình ảnh nhỏ
    byte[] data;               // Dữ liệu đã serialize
}
```

### 2.3. Format dữ liệu gửi đi

**Cấu trúc message:**
```
[Byte] 0                    // Flag/Type (luôn = 0)
[Int]  size                 // Số lượng parts
[Loop size lần]:
    [Short] part.id         // ID của part
    [Short] data.length     // Độ dài dữ liệu
    [Byte[]] part.data      // Dữ liệu đã serialize
[Loop size lần]:
    [Short] part.id         // ID của part (lặp lại)
    [Short] part.id         // ID của part (lặp lại - có vẻ là bug?)
```

**Lưu ý**: Có một vấn đề tiềm ẩn ở dòng 1611: `dos.writeShort(part.id)` được gọi 2 lần liên tiếp, có thể là lỗi logic.

---

## 3. Khi nào được gọi

### 3.1. Trong quá trình khởi tạo game
**File**: `Service.java:304`
```java
public void updateDataStart() {
    // ... load images ...
    updateItemTemplate();
    finish_Intro((byte) 0);
    updateSkill();
    updateCharData();  // ← Được gọi ở đây
}
```

**Mục đích**: Khi người chơi mới vào game, cần tải tất cả dữ liệu cơ bản bao gồm cả dữ liệu parts.

### 3.2. Khi người chơi login vào nhân vật
**File**: `User.java:336`
```java
sltChar.setParts();
service.infoMainChar();
service.updateCharData();  // ← Được gọi ở đây
service.updateCharWearing(this.sltChar);
service.updateInventory();
```

**Mục đích**: Khi người chơi chọn nhân vật và vào game, cần cập nhật dữ liệu parts để hiển thị nhân vật đúng.

---

## 4. Xử lý phía Client

### 4.1. Nhận message
**File**: `Controller.java:49-50`
```java
case CMD.CMD_GET_DATA_CHAR:
    client.getService().requestDataChar(mss);
    break;
```

### 4.2. Request từ Client
Client có thể gửi request `CMD_GET_DATA_CHAR` để yêu cầu server gửi lại dữ liệu parts.

**Điều kiện đặc biệt**: 
- Command này được xử lý ngay cả khi `_char == null` hoặc `user == null` (xem `Controller.java:38-39`)
- Điều này cho phép client request dữ liệu parts trước khi login hoàn tất

---

## 5. Dữ liệu nguồn

### 5.1. GameData.parts
**File**: `GameData.java:136`
```java
public static ArrayList<Part> parts = new ArrayList<>();
```

### 5.2. Load từ Database
**File**: `GameData.java:350-398`

Parts được load từ bảng `part` trong database với các trường:
- `id`: ID của part
- `idBig`: ID hình ảnh lớn
- `type`: Loại part
- `listIDChunk`: JSON array chứa danh sách ID chunk
- `chunkData`: JSON array chứa dữ liệu chunk
- `allSmallImg`: JSON array chứa thông tin hình ảnh nhỏ

Sau khi load, mỗi part sẽ gọi `part.loadData()` để serialize dữ liệu thành `byte[]`.

---

## 6. Vấn đề và cải thiện

### 6.1. Bug tiềm ẩn
**Dòng 1610-1611**:
```java
dos.writeShort(part.id);
dos.writeShort(part.id);  // ← Ghi 2 lần cùng giá trị
```

**Phân tích**: 
- Vòng lặp thứ 2 (1608-1612) ghi `part.id` 2 lần liên tiếp
- Có thể đây là lỗi copy-paste hoặc thiếu sót logic
- Cần kiểm tra client có xử lý đúng format này không

**Đề xuất**: 
- Xem xét lại logic: có thể cần ghi thêm thông tin khác thay vì `part.id` lần 2
- Hoặc có thể loại bỏ vòng lặp thứ 2 nếu không cần thiết

### 6.2. Xử lý lỗi
**Dòng 1616-1617**:
```java
} catch (IOException e) {
    // ← Không có xử lý lỗi
}
```

**Vấn đề**: 
- Không log lỗi
- Không thông báo cho client
- Khó debug khi có sự cố

**Đề xuất**:
```java
} catch (IOException e) {
    Log.error("Error updateCharData: " + e.getMessage(), e);
    // Có thể gửi thông báo lỗi cho client
}
```

### 6.3. Performance
- Dữ liệu parts có thể rất lớn nếu có nhiều parts
- Mỗi lần gọi sẽ serialize và gửi toàn bộ parts
- Không có cơ chế cache hoặc delta update

**Đề xuất**:
- Chỉ gửi parts khi thực sự cần thiết
- Có thể implement versioning để chỉ gửi parts đã thay đổi
- Compress dữ liệu trước khi gửi nếu quá lớn

---

## 7. Yêu cầu kỹ thuật

### 7.1. Dependencies
- `GameData.parts`: Danh sách parts đã load từ database
- `Message`: Class để tạo và gửi message
- `DataOutputStream`: Để serialize dữ liệu
- `Session`: Để gửi message đến client

### 7.2. Điều kiện tiên quyết
- `GameData.parts` phải đã được load (gọi trong `GameData.init()`)
- `Session` phải còn active và chưa đóng
- Mỗi `Part` phải đã gọi `loadData()` để có `data` byte array

### 7.3. Thread Safety
- `GameData.parts` là `ArrayList` - không thread-safe
- Cần đảm bảo không modify `parts` trong khi đang đọc
- Nên sử dụng `Collections.synchronizedList()` hoặc copy list trước khi iterate

---

## 8. Test Cases

### 8.1. Test Case 1: Gửi dữ liệu parts thành công
**Input**: 
- `GameData.parts.size() > 0`
- Session active

**Expected**:
- Message được tạo với CMD = CMD_GET_DATA_CHAR
- Tất cả parts được serialize và gửi
- Client nhận được dữ liệu đúng format

### 8.2. Test Case 2: Không có parts
**Input**:
- `GameData.parts.size() == 0`

**Expected**:
- Message vẫn được gửi với size = 0
- Client nhận được message rỗng

### 8.3. Test Case 3: Session đã đóng
**Input**:
- `session.isClosed == true`

**Expected**:
- Message không được gửi (kiểm tra trong `sendMessage()`)
- Không có exception

### 8.4. Test Case 4: IOException
**Input**:
- Lỗi khi write vào DataOutputStream

**Expected**:
- Exception được catch
- Không crash server
- (Hiện tại không log - cần cải thiện)

---

## 9. Tài liệu tham khảo

### 9.1. Files liên quan
- `Service.java`: Implementation chính
- `AbsService.java`: Base implementation (load từ file)
- `Controller.java`: Xử lý request từ client
- `GameData.java`: Quản lý và load parts từ database
- `Part.java`: Class định nghĩa cấu trúc Part
- `User.java`: Gọi updateCharData khi login

### 9.2. Database Schema
**Bảng `part`**:
```sql
CREATE TABLE part (
    id INT PRIMARY KEY,
    idBig SMALLINT,
    type TINYINT,
    listIDChunk TEXT,      -- JSON array
    chunkData TEXT,        -- JSON array
    allSmallImg TEXT       -- JSON array
);
```

---

## 10. Kết luận

Phương thức `updateCharData()` là một thành phần quan trọng trong hệ thống đồng bộ dữ liệu nhân vật giữa server và client. Nó đảm bảo client có đầy đủ thông tin để render nhân vật chính xác.

**Điểm mạnh**:
- Đơn giản, dễ hiểu
- Tích hợp tốt với hệ thống message hiện có
- Load dữ liệu từ database một cách có tổ chức

**Điểm cần cải thiện**:
- Xử lý lỗi chưa đầy đủ
- Logic có vẻ có bug ở vòng lặp thứ 2
- Chưa tối ưu performance cho trường hợp có nhiều parts
- Thiếu thread safety cho việc đọc `GameData.parts`

**Khuyến nghị**:
1. Sửa bug ở dòng 1611 (ghi `part.id` 2 lần)
2. Thêm logging cho exception
3. Xem xét implement caching hoặc delta update
4. Thêm thread safety cho `GameData.parts`
5. Thêm unit tests cho phương thức này
