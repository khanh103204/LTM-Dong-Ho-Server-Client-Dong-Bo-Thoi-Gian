package ClockSync;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time {

    // Định dạng: yyyy-MM-dd HH:mm:ss.SSS (theo giờ Việt Nam)
    private static final SimpleDateFormat sdf;
    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }

    /** Định dạng mili-giây thành chuỗi thời gian */
    public static String fmt(long epochMillis) {
        return sdf.format(new Date(epochMillis));
    }
}
