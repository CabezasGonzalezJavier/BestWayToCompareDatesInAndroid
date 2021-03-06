package com.thedeveloperworldisyours.bestwaytocomparedatesinandroid.common;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by javierg on 14/06/2017.
 */

public class DateUtil {

    public static int getDateDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String convertMillisSecondsToHourString(long millisSecond) {
        Date date = new Date(millisSecond);
        Format formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    public static String convertMillisSecondsToDateString(long millisSecond) {
        Date date = new Date(millisSecond);
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

    public static long convertToMillisSecond(Date date) {
        return date.getTime();
    }

    public static String compare(String stringData, String yesterday) {

        String result = "";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = simpleDateFormat.parse(stringData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long millisSecond = convertToMillisSecond(date);
        long currencyMillisSecond = System.currentTimeMillis();

        if (currencyMillisSecond > millisSecond) {
            long diff = currencyMillisSecond - millisSecond;
            long day = 86400000L;

            if (diff < day && getCurrentDayOfMonth() == getDateDayOfMonth(date)) {
                result = convertMillisSecondsToHourString(millisSecond);

            } else if (diff < (day * 2) && getCurrentDayOfMonth() -1 == getDateDayOfMonth(date)) {
                result = yesterday;
            } else {
                result = convertMillisSecondsToDateString(millisSecond);
            }
        }

        return result;
    }
}
