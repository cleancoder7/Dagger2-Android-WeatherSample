package com.example.daggerpractice00.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
    private static final String format = "yyyy/MM/dd";

    public String getDate() {
        return getTime();
    }

    public String getTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(cal.getTime());
    }

    public String getDate(int days) {
        Calendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        gc.add(Calendar.DAY_OF_MONTH, days);
        return sdf.format(gc.getTime());
    }

    public String getToday() {
        return getDate();
    }

    public String getTomorrow() {
        return getDate(+1);
    }
}
