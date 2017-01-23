package hr.karlovrbic.notify.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by thekarlo95 on 23.01.17..
 */

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static Date toDate(@NonNull String date) {
        String[] split = date.split("\\.|:|\\s");
        Calendar c = Calendar.getInstance();

        if (split.length >= 5) {
            c.set(Integer.parseInt(split[2].trim()),
                    Integer.parseInt(split[1].trim()) - 1,
                    Integer.parseInt(split[0].trim()),
                    Integer.parseInt(split[3].trim()),
                    Integer.parseInt(split[4].trim()));
        } else if (split.length >= 3) {
            c.set(Integer.parseInt(split[2].trim()),
                    Integer.parseInt(split[1].trim()) - 1,
                    Integer.parseInt(split[0].trim()),
                    0,
                    0);
        }
        return c.getTime();
    }

    public static String toString(Date date) {
        if (date != null) {
            return DATE_FORMAT.format(date);
        } else {
            return null;
        }
    }
}
