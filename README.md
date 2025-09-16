<h2 align="center">
    <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
    🎓 Faculty of Information Technology (DaiNam University)
    </a>
</h2>
<h2 align="center">
   ĐỒNG HỒ SERVER – CLIENT (ĐỒNG BỘ THỜI GIAN)
</h2>
<div align="center">
    <p align="center">
        <img src="docs/aiotlab_logo.png" alt="AIoTLab Logo" width="170"/>
        <img src="docs/fitdnu_logo.png" alt="AIoTLab Logo" width="180"/>
        <img src="docs/dnu_logo.png" alt="DaiNam University Logo" width="200"/>
    </p>

[![AIoTLab](https://img.shields.io/badge/AIoTLab-green?style=for-the-badge)](https://www.facebook.com/DNUAIoTLab)
[![Faculty of Information Technology](https://img.shields.io/badge/Faculty%20of%20Information%20Technology-blue?style=for-the-badge)](https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin)
[![DaiNam University](https://img.shields.io/badge/DaiNam%20University-orange?style=for-the-badge)](https://dainam.edu.vn)

</div>

## 📖 1. Giới thiệu
Đề tài “Đồng hồ Server – Client (đồng bộ thời gian)” tập trung vào việc xây dựng ứng dụng mạng theo mô hình Client Server nhằm thực hiện đồng bộ thời gian giữa các máy tính. Ứng dụng được cài đặt dựa trên cơ chế giao tiếp Sockets và sử dụng giao thức UDP để truyền dữ liệu. UDP được lựa chọn nhờ ưu điểm nhẹ, nhanh, phù hợp với yêu cầu đồng bộ thời gian trong mạng. Thông qua đề tài, sinh viên không chỉ nắm vững nguyên lý truyền nhận dữ liệu bằng UDP, mà còn hiểu rõ cách thiết kế giao thức tầng ứng dụng riêng và rèn luyện kỹ năng lập trình các ứng dụng mạng thực tiễn.

Ứng dụng sử dụng giao thức UDP để truyền nhận dữ liệu, nhờ đó đảm bảo tốc độ và giảm thiểu độ trễ trong quá trình đồng bộ. 
Phía Server đóng vai trò giữ thời gian chuẩn, phản hồi các yêu cầu đồng bộ từ Client và ghi lại thông tin vào file log. 
Phía Client được phát triển với Java Swing, cung cấp giao diện trực quan để hiển thị đồng hồ, cho phép người dùng chủ động đồng bộ với Server.
## 🔧 2. Ngôn ngữ lập trình sử dụng: [![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
- **Ngôn ngữ:** Java  
- **Giao diện người dùng:** Java Swing  
- **Giao thức mạng:** UDP (DatagramSocket, DatagramPacket)  
- **Lưu trữ dữ liệu:** File (Server ghi log request từ Client)  
- **Môi trường phát triển:** Eclipse IDE hoặc IntelliJ IDEA  
- **JDK:** Phiên bản 8 trở lên

## 🚀 3. Các chức năng chính và hình ảnh
Ứng dụng Đồng hồ Server – Client (đồng bộ thời gian) với cấu trúc và chức năng cụ thể như sau:

Server:

Nguồn thời gian chuẩn của hệ thống.

Chức năng:

Lắng nghe yêu cầu từ Client qua UDP (port 4445).

Trả về thời gian theo múi giờ yêu cầu (UTC, VN, US, JP).

Ghi log vào server_log.txt.

Client:

Máy trạm cần đồng bộ với Server.

Chức năng:

Hiển thị giờ cục bộ và giờ sau đồng bộ.

Chọn múi giờ (UTC, VN, US, JP) qua ComboBox.

Nút Đồng bộ gửi yêu cầu, nhận phản hồi và cập nhật.

Tính toán và hiển thị độ trễ (Delay).

Các hình ảnh:

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/82aced08-424f-4cd8-9362-aedeea614df3" />
 </p>

<p align = "center">Hình 1: Giao diện thời gian server </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/206ec908-e334-448a-bb90-38ab2c1e0daa" />
 </p>
<p align = "center">Hình 2: Giao diện thời gian client </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/ed6cd34e-2504-40d8-8477-8d6a1987a454" />
 </p>

<p align = "center">Hình 3: Giao diện chọn múi giờ </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/224a6a77-5d3c-403c-8857-80984eecee72" />
 </p>

<p align = "center">Hình 4: Gia diện đồng bộ múi giờ đã chọn </p>





## 📝 4. Các bước cài đặt

🔹Bước 1: Chuẩn bị môi trường

Cài đặt Java JDK 8 hoặc mới hơn.

Tải tại: https://www.oracle.com/java/technologies/downloads/

Sau khi cài đặt, mở Command Prompt / Terminal và kiểm tra:

java -version


Nếu hiển thị java version "1.8.x" hoặc cao hơn nghĩa là cài đặt thành công.

Cài đặt một IDE để chạy code dễ dàng (khuyến nghị IntelliJ IDEA, có thể dùng Eclipse hoặc NetBeans).

🔹Bước 2: Tải mã nguồn

Clone dự án từ GitHub:

git clone [https://github.com/your-repo/ClockSync-App.git
cd ClockSync-App](https://github.com/khanh103204/LTM-Dong-Ho-Server-Client-Dong-Bo-Thoi-Gian.git)


Nếu không dùng Git, bạn có thể bấm Download ZIP trên GitHub → giải nén.

🔹Bước 3: Mở dự án trong IDE

Vào File → Open Project trong IDE.

Chọn thư mục chứa source code (Client.java và Server.java).

Đảm bảo IDE nhận diện dự án là Java Project.

🔹Bước 4: Biên dịch và chạy Server

Mở file Server.java.

Nhấn Run để chạy server.

Server sẽ lắng nghe kết nối trên port 4445.

Nếu chạy thành công, IDE sẽ hiển thị log kiểu:

[Server] Đang chạy tại cổng 4445...
Chờ yêu cầu từ Client...


🔹Bước 5: Biên dịch và chạy Client

Mở file Client.java.

Nhấn Run để chạy client.

Giao diện hiển thị:

Ngày tháng năm.

Đồng hồ Việt Nam chạy mặc định.

ComboBox chọn múi giờ (UTC, Việt Nam, Mỹ, Nhật).

Nút Đồng bộ giờ.

🔹Bước 6: Đồng bộ thời gian

Người dùng chọn múi giờ từ ComboBox (ví dụ: UTC).

Nhấn Đồng bộ giờ.

Client gửi yêu cầu đến Server → Server trả về thời gian chuẩn theo múi giờ đã chọn.

Client cập nhật và hiển thị đồng hồ theo múi giờ đó.

Log đồng bộ sẽ được lưu tại file server_log.txt trên Server để kiểm tra sau.

## 👥 5. Liên hệ
