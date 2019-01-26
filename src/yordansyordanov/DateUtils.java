package yordansyordanov;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * @param date
     * @param days
     * @return new Date +- days the given date
     */
    public static Date addDays(Date date, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }


    public static String format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-YY HH:mm:ss SSS");
        return simpleDateFormat.format(date);
    }
}
