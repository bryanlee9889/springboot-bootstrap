package com.averagejoedev.utils;

import java.util.Calendar;

/**
 * Created by voncount on 8/4/16.
 */
public class DateTimeUtils {

    public static long now() {
        return Calendar.getInstance().getTime().getTime();
    }

    public static long addTime(int value, int type) {
        Calendar now = Calendar.getInstance();
        now.add(type, value);
        return now.getTime().getTime();
    }

}
