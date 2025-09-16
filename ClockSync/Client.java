package ClockSync;

import javax.swing.*;
import java.awt.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Client extends JFrame {

    private JLabel vnClockLabel, zoneClockLabel, syncedClockLabel, dateLabel, offsetLabel;
    private JComboBox<String> zoneBox;
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 4445;

    public Client() {
        setTitle("ĐỒNG HỒ CLIENT");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel);

        // Ngày tháng năm tiếng Việt
        dateLabel = new JLabel(
                new SimpleDateFormat("EEEE, d MMMM, yyyy", new Locale("vi", "VN")).format(new Date()),
                SwingConstants.CENTER
        );
        dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        dateLabel.setForeground(Color.BLACK);
        mainPanel.add(dateLabel, BorderLayout.NORTH);

        // Panel trung tâm chứa đồng hồ
        JPanel clockPanel = new JPanel(new GridLayout(3, 2, 30, 15));
        clockPanel.setBackground(Color.WHITE);

        JLabel vnTitle = new JLabel("Giờ Việt Nam", SwingConstants.CENTER);
        vnTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        vnTitle.setForeground(Color.BLACK);
        clockPanel.add(vnTitle);

        vnClockLabel = createClockLabel("--:--:--");
        clockPanel.add(vnClockLabel);

        JLabel zoneTitle = new JLabel("Giờ theo lựa chọn", SwingConstants.CENTER);
        zoneTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        zoneTitle.setForeground(Color.BLACK);
        clockPanel.add(zoneTitle);

        zoneClockLabel = createClockLabel("--:--:--");
        clockPanel.add(zoneClockLabel);

        JLabel syncedTitle = new JLabel("Sau khi đồng bộ", SwingConstants.CENTER);
        syncedTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        syncedTitle.setForeground(Color.BLACK);
        clockPanel.add(syncedTitle);

        syncedClockLabel = createClockLabel("--:--:--");
        clockPanel.add(syncedClockLabel);

        mainPanel.add(clockPanel, BorderLayout.CENTER);

        // Panel dưới
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        // Chọn múi giờ
        zoneBox = new JComboBox<>(new String[]{"UTC", "VN", "US", "JP"});
        zoneBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        zoneBox.setMaximumSize(new Dimension(200, 40));
        zoneBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(zoneBox);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        offsetLabel = new JLabel("Chênh lệch: -- ms", SwingConstants.CENTER);
        offsetLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        offsetLabel.setForeground(Color.DARK_GRAY);
        offsetLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(offsetLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton syncBtn = new JButton("Đồng bộ giờ");
        syncBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        syncBtn.setBackground(Color.WHITE);
        syncBtn.setForeground(Color.BLACK);
        syncBtn.setFocusPainted(false);
        syncBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(syncBtn);

        syncBtn.addActionListener(e -> doSync());

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Timer cập nhật giờ
        new Timer(1000, e -> updateClocks()).start();

        setVisible(true);
    }

    private JLabel createClockLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Consolas", Font.BOLD, 48));
        label.setForeground(Color.BLACK);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return label;
    }

    private void updateClocks() {
        Date now = new Date();

        // Giờ Việt Nam
        SimpleDateFormat vnFormat = new SimpleDateFormat("HH:mm:ss");
        vnFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        vnClockLabel.setText(vnFormat.format(now));

        // Giờ theo lựa chọn
        String zone = (String) zoneBox.getSelectedItem();
        SimpleDateFormat zoneFormat = new SimpleDateFormat("HH:mm:ss");

        switch (zone) {
            case "UTC":
                zoneFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                break;
            case "VN":
                zoneFormat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                break;
            case "US":
                zoneFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
                break;
            case "JP":
                zoneFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
                break;
        }
        zoneClockLabel.setText(zoneFormat.format(now));
    }

    private void doSync() {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress addr = InetAddress.getByName(SERVER_IP);

            String zone = (String) zoneBox.getSelectedItem();
            long T1 = System.currentTimeMillis();
            byte[] out = ("REQ " + zone).getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(out, out.length, addr, SERVER_PORT);
            socket.send(packet);

            byte[] buf = new byte[256];
            DatagramPacket resp = new DatagramPacket(buf, buf.length);
            socket.receive(resp);
            long T4 = System.currentTimeMillis();

            String respStr = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8).trim();

            // Ví dụ: "VN 2025-09-16 20:15:30"
            String[] parts = respStr.split(" ", 2);
            if (parts.length == 2) {
                String timeStr = parts[1];
                long delay = T4 - T1;
                offsetLabel.setText("Chênh lệch: " + delay / 2 + " ms");
                syncedClockLabel.setText(timeStr.substring(11)); // chỉ HH:mm:ss
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi đồng bộ: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}
