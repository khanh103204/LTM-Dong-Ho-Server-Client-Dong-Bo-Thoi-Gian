package ClockSync;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Client extends JFrame {

    private static final String SERVER_HOST = "127.0.0.1"; // sửa thành IP Server nếu chạy khác máy
    private static final int SERVER_PORT = 4445;
    private static final int BUFFER_SIZE = 1024;

    private JLabel localTimeLabel;
    private JLabel syncedTimeLabel;
    private JLabel offsetLabel;
    private Timer clockTimer;

    private long offsetMs = 0; // độ lệch tính được

    public Client() {
        setTitle("UDP Clock Client");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI components
        localTimeLabel = new JLabel("Local Time: ");
        syncedTimeLabel = new JLabel("Synced Time: ");
        offsetLabel = new JLabel("Offset: 0 ms");

        JButton syncButton = new JButton("Đồng bộ");
        syncButton.addActionListener(this::onSync);

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.add(localTimeLabel);
        panel.add(syncedTimeLabel);
        panel.add(offsetLabel);
        panel.add(syncButton);

        add(panel);

        // Timer cập nhật đồng hồ mỗi giây
        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();
    }

    private void updateClock() {
        long now = System.currentTimeMillis();
        localTimeLabel.setText("Local Time: " + Time.fmt(now));
        syncedTimeLabel.setText("Synced Time: " + Time.fmt(now + offsetMs));
    }

    private void onSync(ActionEvent e) {
        try {
            SyncResult res = syncWithServer();
            if (res != null) {
                offsetMs = res.offsetMs;
                offsetLabel.setText("Offset: " + offsetMs + " ms");
                JOptionPane.showMessageDialog(this,
                        "Đồng bộ thành công!\nDelay = " + res.delayMs + " ms\nOffset = " + res.offsetMs + " ms",
                        "Kết quả", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Không thể đồng bộ với Server!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(),
                    "Exception", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // ====== Hàm đồng bộ (giống Client trước nhưng tích hợp vào Swing) ======
    private SyncResult syncWithServer() throws Exception {
        InetAddress serverAddr = InetAddress.getByName(SERVER_HOST);
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(2000);

            String id = String.valueOf(new Random().nextInt(1_000_000));
            long T1 = System.currentTimeMillis();
            String payload = "REQ " + id + " " + T1;
            byte[] out = payload.getBytes(StandardCharsets.UTF_8);

            DatagramPacket req = new DatagramPacket(out, out.length, serverAddr, SERVER_PORT);
            socket.send(req);

            byte[] buf = new byte[BUFFER_SIZE];
            DatagramPacket resp = new DatagramPacket(buf, buf.length);

            socket.receive(resp);
            long T4 = System.currentTimeMillis();

            String ans = new String(resp.getData(), 0, resp.getLength(), StandardCharsets.UTF_8).trim();
            String[] parts = ans.split("\\s+");
            if (parts.length < 5 || !"RES".equalsIgnoreCase(parts[0])) return null;

            long t1 = Long.parseLong(parts[2]);
            long t2 = Long.parseLong(parts[3]);
            long t3 = Long.parseLong(parts[4]);

            long offset = ((t2 - t1) + (t3 - T4)) / 2;
            long delay = (T4 - t1) - (t3 - t2);

            return new SyncResult(t1, t2, t3, T4, offset, delay);
        }
    }

    // Class lưu kết quả đồng bộ
    private static class SyncResult {
        long t1, t2, t3, t4;
        long offsetMs;
        long delayMs;

        public SyncResult(long t1, long t2, long t3, long t4, long offsetMs, long delayMs) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
            this.offsetMs = offsetMs;
            this.delayMs = delayMs;
        }
    }

    // Main chạy Client
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Client().setVisible(true);
        });
    }
}
