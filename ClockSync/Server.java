package ClockSync;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Server extends JFrame {

    private JLabel dateLabel;
    private JLabel utcLabel, vnLabel, usLabel, jpLabel;
    private JTextArea logArea;
    public static final int PORT = 4445;

    public Server() {
        // ==== Frame mặc định ====
        setTitle("UDP Time Server");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ==== Ngày tháng ====
        dateLabel = new JLabel("--", SwingConstants.CENTER);
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        dateLabel.setForeground(new Color(20, 20, 20));
        add(dateLabel, BorderLayout.NORTH);

        // ==== Panel các múi giờ ====
        JPanel timePanel = new JPanel(new GridLayout(2, 2, 20, 20));
        timePanel.setBackground(new Color(230, 230, 230));
        timePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        utcLabel = createZoneClock("UTC");
        vnLabel = createZoneClock("Việt Nam");
        usLabel = createZoneClock("Mỹ (New York)");
        jpLabel = createZoneClock("Nhật Bản");

        timePanel.add(utcLabel);
        timePanel.add(vnLabel);
        timePanel.add(usLabel);
        timePanel.add(jpLabel);

        add(timePanel, BorderLayout.CENTER);

        // ==== Log area ====
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setPreferredSize(new Dimension(getWidth(), 150));
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Server Log"));
        add(scrollPane, BorderLayout.SOUTH);

        // ==== Cập nhật đồng hồ ====
        new Timer(1000, e -> updateTime()).start();

        setVisible(true);

        // ==== Start UDP server ====
        new Thread(this::startServer).start();
    }

    private JLabel createZoneClock(String zoneName) {
        JLabel lbl = new JLabel(zoneName + ": --:--:--", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        lbl.setForeground(Color.BLACK);
        lbl.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return lbl;
    }

    private void updateTime() {
        Date now = new Date();
        // Ngày tiếng Việt
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("vi"));
        dateLabel.setText(dateFormat.format(now));

        utcLabel.setText("UTC: " + formatTime("UTC"));
        vnLabel.setText("Việt Nam: " + formatTime("Asia/Ho_Chi_Minh"));
        usLabel.setText("Mỹ : " + formatTime("America/New_York"));
        jpLabel.setText("Nhật Bản: " + formatTime("Asia/Tokyo"));
    }

    private String formatTime(String tz) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        return sdf.format(new Date());
    }

    private void startServer() {
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            log("[Server] Đang chạy trên cổng " + PORT);

            byte[] buf = new byte[256];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String req = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8).trim();
                log("[Server] Yêu cầu: " + req);

                if (req.startsWith("REQ")) {
                    String zone = req.length() > 3 ? req.substring(3).trim().toUpperCase() : "UTC";
                    String timeResp = getTimeByZone(zone);

                    byte[] out = timeResp.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket respPacket = new DatagramPacket(out, out.length, packet.getAddress(), packet.getPort());
                    socket.send(respPacket);

                    String msg = "Client " + packet.getAddress() + ":" + packet.getPort()
                            + " đã đồng bộ (" + zone + ") => " + timeResp;

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(
                                this,
                                msg,
                                "Thông báo đồng bộ",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        log("[Server] " + msg);
                        saveLog(msg);
                    });

                } else {
                    log("[Server] Yêu cầu không xác định: " + req);
                }
            }
        } catch (Exception e) {
            log("[Server] Lỗi: " + e.getMessage());
        }
    }

    private String getTimeByZone(String zone) {
        String tzID;
        switch (zone) {
            case "VN": tzID = "Asia/Ho_Chi_Minh"; break;
            case "US": tzID = "America/New_York"; break;
            case "JP": tzID = "Asia/Tokyo"; break;
            default: tzID = "UTC"; zone = "UTC"; break;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone(tzID));
        return zone + " " + sdf.format(new Date());
    }

    private void log(String msg) {
        SwingUtilities.invokeLater(() -> logArea.append(msg + "\n"));
    }

    private void saveLog(String msg) {
        try (FileWriter fw = new FileWriter("server_log.txt", true)) {
            fw.write(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + " - " + msg + "\n");
        } catch (IOException e) {
            log("[Server] Không thể ghi log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Server::new);
    }
}
