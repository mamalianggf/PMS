package com.pms.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 获取当前时间的年月日-时分秒
     *
     * @return
     */
    public static String now() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(LocalDateTime.now());
    }
}
