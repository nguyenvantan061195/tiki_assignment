/**
 * Copyright 2017 Lacy . All rights reserved.
 * Lacy PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.lacy.example.keywork.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author DUNGNX3
 * Chua cac ham util ve date
 */
public class DateUtils {
    /**
     * The Constant SECOND.
     */
    public static final long SECOND = 1000;

    /**
     * The Constant MINUTE.
     */
    public static final long MINUTE = SECOND * 60;

    /**
     * The Constant HOUR.
     */
    public static final long HOUR = MINUTE * 60;
    // chua kiem tra duoc thoi gian
    public final static int TIME_NOT_CHECK = -1;
    // thoi gian khong hop le
    public final static int TIME_INVALID = 0;
    // thoi gian hop le
    public final static int TIME_VALID = 1;

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_VN = "HH:mm dd/MM/yyyy";
    public static final String DATE_STRING_YYYY_MM_DD = "yyyy-MM-dd";
    public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat defaultDateFormat_2 = new SimpleDateFormat(DATE_FORMAT_NOW);
    public static final String defaultDateFormat_3 = new String("dd/MM/yyyy HH:mm");
    public static final SimpleDateFormat defaultSqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat defaultSqlFullDateFormat = new SimpleDateFormat(DATE_FORMAT_NOW);
    public static final String DATE_FORMAT_ATTENDANCE = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_HOUR_MINUTE = "HH:mm";
    public static final SimpleDateFormat defaultHourMinute = new SimpleDateFormat(DATE_FORMAT_HOUR_MINUTE);
    public static final String DATE_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DATE_PAY_RECEIVED = "ddMMyy";
    public static final String DATE_TIME_FORMAT_TIME = "HH:mm dd/MM";
    public static final String DATE_FORMAT_FILE_EXPORT = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_DATE_VN = "dd/MM/yyyy";

    public static final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

    // thời gian sai lệch tối đa 10p
    public static final long MAX_TIME_WRONG = 10;
    public static final int RIGHT_TIME = 0;
    public static final int WRONG_DATE = 1;
    public static final int WRONG_TIME = 2;
    public static final int WRONG_TIME_BOOT_REASON = 3;

    public static final int GMT7Offset = 60 * 60 * 1000 * 7;

    /**
     * Lay thoi gian hien tai theo: yyyy-MM-dd HH:mm:SS
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    /**
     * Convert date sang mot dinh dang truyen vao
     */
    public static String convertDateTimeWithFormat(Date date, String format) {
        if (date != null) {
            if (StringUtil.isNullOrEmpty(format)) {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                return sdf.format(date);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(date);
            }
        } else {
            return null;
        }
    }

    /**
     * Lay thoi gian theo format truyen vao
     */
    public static String getCurrentDateTimeWithFormat(String strFormat) {
        Date currentDateTime = new Date();
        SimpleDateFormat format = null;
        if (StringUtil.isNullOrEmpty(strFormat)) {
            format = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        } else {
            format = new SimpleDateFormat(strFormat);
        }
        return format.format(currentDateTime);
    }

    /**
     * getCurrentDate
     */
    public static int getCurrentDay() {
        Date d = new Date(System.currentTimeMillis());
        Calendar.getInstance().setTime(d);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int indexDay = -1;
        switch (day) {
            case Calendar.MONDAY:
                indexDay = 0;
                break;
            case Calendar.TUESDAY:
                indexDay = 1;
                break;
            case Calendar.WEDNESDAY:
                indexDay = 2;
                break;
            case Calendar.THURSDAY:
                indexDay = 3;
                break;
            case Calendar.FRIDAY:
                indexDay = 4;
                break;
            case Calendar.SATURDAY:
                indexDay = 5;
                break;
            case Calendar.SUNDAY:
                indexDay = 6;
                break;
        }
        return indexDay;
    }
}
