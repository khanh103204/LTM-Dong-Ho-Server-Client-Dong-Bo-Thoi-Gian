package ClockSync;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class File {

    private static final String LOG_FILE = "server_log.txt";
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void log(String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String time = LocalDateTime.now().format(fmt);
            out.println(time + " - " + message);
        } catch (IOException e) {
            System.err.println("[Logger] Error: " + e.getMessage());
        }
    }
}
