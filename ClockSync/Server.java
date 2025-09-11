package ClockSync;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Server {

    public static final int PORT = 4445;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        System.out.println("[Server] UDP Time Server running on port " + PORT);

        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            socket.setReuseAddress(true);

            byte[] buf = new byte[BUFFER_SIZE];

            while (true) {
                // Nhận gói tin từ Client
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String req = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8).trim();
                long T2 = System.currentTimeMillis(); // thời điểm server nhận

                String[] parts = req.split("\\s+");
                if (parts.length >= 3 && "REQ".equalsIgnoreCase(parts[0])) {
                    String id = parts[1];
                    String T1str = parts[2];

                    long T3 = System.currentTimeMillis(); // thời điểm server gửi
                    String resp = "RES " + id + " " + T1str + " " + T2 + " " + T3;
                    byte[] out = resp.getBytes(StandardCharsets.UTF_8);

                    InetAddress clientAddr = packet.getAddress();
                    int clientPort = packet.getPort();

                    // Gửi phản hồi
                    DatagramPacket respPacket = new DatagramPacket(out, out.length, clientAddr, clientPort);
                    socket.send(respPacket);

                    // Ghi log
                    String logLine = String.format("Client=%s:%d, ID=%s, T1=%s, T2=%d, T3=%d",
                            clientAddr.getHostAddress(), clientPort, id, T1str, T2, T3);
                    File.log(logLine);

                    System.out.println("[Server] " + logLine);
                } else {
                    System.out.println("[Server] Unknown request: " + req);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
