<h2 align="center">
    <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
    🎓 Faculty of Information Technology (DaiNam University)
    </a>
</h2>
<h2 align="center">
   Đồng hồ Server – Client (đồng bộ thời gian)
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
Đề tài “Đồng hồ Server – Client (đồng bộ thời gian)” tập trung vào việc xây dựng ứng dụng mạng theo mô hình Client/Server nhằm thực hiện đồng bộ thời gian giữa các máy tính. Ứng dụng được cài đặt dựa trên cơ chế giao tiếp Sockets và sử dụng giao thức UDP để truyền dữ liệu. UDP được lựa chọn nhờ ưu điểm nhẹ, nhanh, phù hợp với yêu cầu đồng bộ thời gian trong mạng. Thông qua đề tài, sinh viên không chỉ nắm vững nguyên lý truyền nhận dữ liệu bằng UDP, mà còn hiểu rõ cách thiết kế giao thức tầng ứng dụng riêng và rèn luyện kỹ năng lập trình các ứng dụng mạng thực tiễn.

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
Server

Giữ vai trò thời gian chuẩn cho toàn hệ thống.

Lắng nghe yêu cầu từ Client qua UDP socket (port 4445).

Ghi nhận các mốc thời gian T2, T3 để phản hồi cho Client.

Gửi gói tin phản hồi chứa T1, T2, T3 giúp Client tính toán Delay và Offset.

Xử lý dữ liệu:

Lưu toàn bộ yêu cầu và kết quả đồng bộ từ Client vào file server_log.txt.

Hỗ trợ kiểm chứng, đánh giá quá trình đồng bộ.

Client

Đóng vai trò máy trạm cần đồng bộ thời gian với Server.

Thực hiện:

Gửi gói tin chứa T1 (thời điểm gửi) đến Server.

Nhận phản hồi từ Server chứa T1, T2, T3.

Ghi nhận T4 (thời điểm nhận phản hồi).

Chức năng chính:

Tính toán theo công thức:

Delay = (T4 – T1) – (T3 – T2)

Offset = ((T2 – T1) + (T3 – T4)) / 2

Cập nhật đồng hồ hiển thị dựa trên thời gian cục bộ + offset.

Các hình ảnh:

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/fb5348b6-067c-4827-bcb1-d95ebe91dac3"/> </p>

<p align = "center">Hình 1: Giao diện đồng bộ thời gian </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/d46ba43b-0177-4abf-9748-22748601b335" /> </p>
<p align = "center">Hình 2: Giao diện bấm giờ </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/10680fe9-d7b6-4f83-91f9-c28ae5401302" /> </p>

<p align = "center">Hình 3: Giao diện hẹn giờ </p>

<p align = "center"> <img width="848" height="609" alt="image" src="https://github.com/user-attachments/assets/7613a16c-1b0d-4348-8e33-33275fb889c3" /> </p>

<p align = "center">Hình 4: Giao diện báo thức </p>






### [Khoá 16](./docs/projects/K16/README.md)

## 📝 4. License
Đề tài Đồng hồ Server – Client (đồng bộ thời gian) được phát triển trong khuôn khổ học phần Lập trình mạng – Khoa Công nghệ Thông tin, Đại học Đại Nam.

Toàn bộ mã nguồn và tài liệu thuộc quyền sở hữu của nhóm sinh viên thực hiện và chỉ phục vụ cho mục đích học tập, nghiên cứu.
Mọi hình thức sao chép, chỉnh sửa hoặc sử dụng cho mục đích thương mại đều cần được sự cho phép của tác giả và khoa chuyên môn

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
👥 5. Liên hệ:
