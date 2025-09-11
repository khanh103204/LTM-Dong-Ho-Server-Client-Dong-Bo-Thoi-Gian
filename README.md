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

## 🚀 3. Các project đã thực hiện
Ứng dụng Đồng hồ Server – Client (đồng bộ thời gian) với các chức năng chính:

Server:

Quản lý và cung cấp thời gian chuẩn cho các Client.

Lưu trữ dữ liệu đồng bộ thời gian vào File/Database để phục vụ kiểm tra và đánh giá.

Client:

Kết nối đến Server thông qua giao thức UDP.

Gửi yêu cầu và nhận thời gian đồng bộ từ Server.

Hiển thị thời gian bằng giao diện Java Swing.

Cơ chế đồng bộ:

Áp dụng giao thức UDP để trao đổi dữ liệu với tốc độ cao.

Thực hiện mô hình đồng bộ dựa trên Client/Server, đảm bảo thời gian giữa các Client sát với thời gian chuẩn trên Server.

### [Khoá 16](./docs/projects/K16/README.md)

## 📝 4. License

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---
