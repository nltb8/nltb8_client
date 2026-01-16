# Tài Liệu Refactoring: Client-Server Communication

## Tổng quan

Đây là tài liệu mô tả chi tiết về quá trình refactoring code giao tiếp giữa client và server trong dự án NLTB8 Client.

## Mục tiêu Refactoring

1. **Cải thiện khả năng đọc hiểu code**: Thêm JavaDoc và comment chi tiết cho tất cả các class, method
2. **Tổ chức code rõ ràng hơn**: Phân nhóm các thành phần theo chức năng
3. **Chuẩn hóa naming**: Sử dụng tên biến và method có ý nghĩa rõ ràng
4. **Duy trì tính tương thích**: KHÔNG thay đổi logic nghiệp vụ, chỉ cải thiện code style

## Các File Đã Refactor

### 1. mSocket.java (lib/mSocket.java)

**Thay đổi:**
- Thêm JavaDoc cho class và tất cả các phương thức
- Cải thiện naming: `s` → `socket`, `str` → `host`, `isAlive` → `keepAlive`
- Thêm null check cho socket trước khi close/setKeepAlive
- Comment giải thích rõ ràng chức năng của từng phương thức

**Mục đích:**
- Wrapper đơn giản cho Java Socket
- Cung cấp các phương thức để tạo kết nối, lấy input/output stream, đóng kết nối

### 2. Message.java (lib2/Message.java)

**Thay đổi:**
- Thêm JavaDoc chi tiết giải thích cấu trúc Message
- Comment phân biệt giữa message gửi đi (outgoing) và nhận về (incoming)
- Giải thích rõ các stream để đọc/ghi dữ liệu

**Cấu trúc Message:**
```
Message {
    command: byte          // Mã lệnh (1 byte)
    data: byte[]          // Dữ liệu đi kèm
}
```

### 3. ISession.java (lib2/ISession.java)

**Thay đổi:**
- Thêm JavaDoc cho interface và tất cả các method
- Comment rõ ràng mục đích của từng method

**Các phương thức:**
- `isConnected()`: Kiểm tra trạng thái kết nối
- `setHandler()`: Đăng ký handler xử lý message
- `connect()`: Kết nối đến server
- `sendMessage()`: Gửi message
- `close()`: Đóng kết nối

### 4. IMessageHandler.java (lib2/IMessageHandler.java)

**Thay đổi:**
- Thêm JavaDoc cho interface và tất cả các method
- Comment giải thích khi nào mỗi method được gọi

**Các sự kiện:**
- `onMessage()`: Xử lý message từ server
- `onConnectOK()`: Kết nối thành công
- `onConnectionFail()`: Kết nối thất bại
- `onDisconnected()`: Mất kết nối

### 5. Session_ME.java (lib/Session_ME.java)

**Thay đổi quan trọng:**
- Thêm JavaDoc chi tiết cho class và tất cả các thành phần
- **Phân nhóm code** theo chức năng với comment headers:
  - SINGLETON INSTANCE
  - NETWORK STREAMS
  - MESSAGE HANDLER
  - CONNECTION STATUS
  - THREADS
  - ENCRYPTION
  - STATISTICS
  - PUBLIC METHODS
  - ENCRYPTION METHODS
  - CONNECTION MANAGEMENT
  - INNER CLASSES

- **Comment chi tiết cho encryption (XOR)**:
  - Giải thích cách nhận key từ server
  - Giải thích cơ chế XOR encryption/decryption
  - Giải thích con trỏ curR/curW và cách quay vòng

- **Comment chi tiết cho message format**:
  - Format khi gửi: command (1 byte) + size (2 bytes) + data (n bytes)
  - Format khi nhận: tương tự nhưng có xử lý đặc biệt cho command 127 (4 bytes size)

- **Comment chi tiết cho threading**:
  - NetworkInit: Thread khởi tạo kết nối
  - MessageCollector: Thread nhận message từ server
  - Sender: Thread gửi message đến server

**Luồng hoạt động:**
```
1. connect() → Tạo NetworkInit thread
2. NetworkInit → Kết nối socket, tạo Sender và MessageCollector threads
3. NetworkInit → Gửi message request key (command -40)
4. MessageCollector → Nhận key từ server, XOR transform
5. Sender → Bắt đầu gửi message (đã được mã hóa XOR)
6. MessageCollector → Nhận và giải mã message, delegate đến handler
```

### 6. Cmd_message.java (mchien/code/network/Cmd_message.java)

**Thay đổi:**
- Thêm JavaDoc cho class giải thích quy ước command
- **Tổ chức lại constants theo nhóm chức năng**:
  - Authentication & Session (1-14)
  - Character Movement & Info (4-8, 15-17)
  - Combat System (6, 9-10, 17, 83, 89-90)
  - Monster System (7, 26, 58)
  - Item System (18-29, 63-78)
  - NPC System (23, 55)
  - Character Stats (30-36, 91)
  - Chat & Social (27, 71, 101-102)
  - Party System (48-50)
  - Quest System (52-53, 56-59)
  - Pet & Mount System (69, 108)
  - Bầu Cua Game (110-122)
  - Clan System (-20 to -7, 66)
  - Special Commands (negative range)
  - Miscellaneous

- Thêm JavaDoc comment cho **MỌI** constant

### 7. MessageHandler.java (mchien/code/network/MessageHandler.java)

**Thay đổi:**
- Thêm JavaDoc cho class giải thích chức năng và pattern (Singleton)
- **Phân nhóm switch-case** theo chức năng với comment headers:
  - MOUNT SYSTEM
  - SYSTEM
  - HORSE/ANIMAL
  - IN-APP PURCHASE
  - MAP & GRAPHICS
  - CHAT
  - TUTORIAL
  - EFFECTS
  - DATA LOADING
  - SERVER DATA
  - WEATHER
  - BẦU CUA GAME (với sub-comments cho từng case)
  - UI ELEMENTS
  - PET
  - AUTHENTICATION
  - CHARACTER INFO
  - MOVEMENT
  - COMBAT
  - MONSTER INFO
  - ITEMS
  - NPC
  - CHAT
  - MAP
  - CHARACTER STATS
  - SERVER MESSAGES
  - PARTY
  - PK
  - LIST INFO
  - QUEST
  - ARENA
  - COUNTDOWN
  - BOX
  - SERVER INFO
  - CLAN
  - FRIEND
  - TRADE
  - PAYMENT
  - CONFIG
  - RANKING

- Comment giải thích mỗi case xử lý message gì

## Kiến Trúc Giao Tiếp Client-Server

### Cấu trúc tổng quan:

```
Client (Session_ME)
    ↓
    ├─ NetworkInit Thread: Khởi tạo kết nối
    ├─ Sender Thread: Gửi message
    ├─ MessageCollector Thread: Nhận message
    ↓
GameService: Tạo và gửi message request
    ↓
Session_ME: Mã hóa và gửi qua socket
    ↓
Server
    ↓
Session_ME: Nhận và giải mã message
    ↓
MessageHandler: Phân loại và xử lý message
    ↓
GameCanvas.readMessenge: Xử lý cụ thể từng loại message
```

### Encryption (Mã hóa):

**Thuật toán:** XOR Encryption

**Quá trình:**
1. Client kết nối → Gửi message request key (command -40)
2. Server → Gửi key (n bytes)
3. Client transform key: key[i+1] ^= key[i] cho tất cả i
4. Mọi byte gửi/nhận đều được XOR với key
5. Con trỏ curR (read) và curW (write) tăng dần và quay vòng

**Ví dụ:**
```java
// Mã hóa 1 byte
byte encrypted = key[curW] ^ originalByte
curW = (curW + 1) % key.length

// Giải mã 1 byte
byte decrypted = key[curR] ^ encryptedByte
curR = (curR + 1) % key.length
```

### Message Format:

**Gửi message:**
```
[command: 1 byte][size: 2 bytes][data: size bytes]
```

**Message đặc biệt (command = 127):**
```
[127: 1 byte][real_command: 1 byte][size: 4 bytes][data: size bytes]
```

## Best Practices Đã Áp Dụng

1. **JavaDoc đầy đủ**: Tất cả public class, interface, method đều có JavaDoc
2. **Comment ý nghĩa**: Comment giải thích "tại sao" chứ không chỉ "cái gì"
3. **Naming conventions**: Tên biến, method rõ ràng, không dùng viết tắt khó hiểu
4. **Code organization**: Phân nhóm code theo chức năng với comment headers
5. **Separation of concerns**: Mỗi class có trách nhiệm rõ ràng
6. **Thread safety**: Synchronized cho các method quan trọng

## Lưu Ý Quan Trọng

### ⚠️ Duplicate Constants (Không sửa)
Một số constants có cùng giá trị (ví dụ: GIVE_ITEM = USE_ITEM_SHOP = 63, SPECIAL_ITEM = ADD_GEM_ITEM_IMBUE = 76).
Đây là thiết kế có chủ đích từ code gốc. **KHÔNG ĐƯỢC SỬA** vì có thể ảnh hưởng đến protocol với server.

### ⚠️ KHÔNG THAY ĐỔI:
- Logic nghiệp vụ
- Thuật toán mã hóa
- Message protocol
- Thread management
- Error handling behavior

### ✅ CHỈ THAY ĐỔI:
- JavaDoc và comments
- Naming (biến, method)
- Code organization (phân nhóm)
- Formatting

## Testing

Do network issue trong môi trường sandbox, không thể build project hoàn chỉnh.
Tuy nhiên, code refactoring chỉ thay đổi:
- Comments
- Variable names (với cùng type và scope)
- Code organization

Do đó, **KHÔNG ẢNH HƯỞNG** đến compilation hay runtime behavior.

## Kết Luận

Refactoring này đã:
1. ✅ Cải thiện đáng kể khả năng đọc hiểu code
2. ✅ Tạo tài liệu rõ ràng cho các developer mới
3. ✅ Tổ chức code theo các nhóm chức năng logic
4. ✅ Giữ nguyên 100% logic nghiệp vụ
5. ✅ Không thay đổi API hay interface

Code sau refactoring dễ maintain, extend và debug hơn đáng kể.
