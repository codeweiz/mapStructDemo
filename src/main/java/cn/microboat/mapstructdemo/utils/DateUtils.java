package cn.microboat.mapstructdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhouwei
 */
public class DateUtils {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public String toDateString(String string) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string);
        return simpleDateFormat.format(new Date());
    }

    public static String date2Format(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return simpleDateFormat.format(date);
    }
}
