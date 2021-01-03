/*
 * DateUtil.java
 *
 * Created on February 21, 2003, 8:25 AM
 * Copyright 2002-2005 Kevin Delargy.
 */

package com.modelgenerated.util;

import com.modelgenerated.foundation.logging.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author  kevind
 */
public class DateUtil {
    public static long MILLISECONDS_PER_DAY = 86400000;
    public static long MILLISECONDS_PER_MINUTE = 60000;
    public static long MILLISECONDS_PER_SECOND = 1000; 


    private static String LOGGER_CATEGORY = "com.modelgenerated.util.DateUtil";
    

    public static Date parseDate(String date) {
        return parseDate(date, "MM/dd/yyyy", "MM-dd-yyyy");
    }

    public static Date parseDate(String date, String pattern) { 
        return parseDate(date, pattern, pattern);
    }
        
        
    public static Date parseDate(String date, String pattern1, String pattern2) {
        if (StringUtil.isEmpty(date)) {
            return null;
        }
        Date returnDate = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        dateFormatter.applyPattern(pattern1);
        try {                             
            returnDate = dateFormatter.parse(date);                
        } catch (Exception e) {
            // do nothing
        }
        if (returnDate == null) { 
            dateFormatter.applyPattern(pattern2);
            try {                             
                returnDate = dateFormatter.parse(date);                
            } catch (Exception e) {
                // do nothing. drop through and return null.
                Logger.warn(LOGGER_CATEGORY, e);                    
            }
        }
        return returnDate;
    }

    /*
     * returns the month part of the date
     *
     * WARNING: This is one-based. Not zero-based like Calendar.
     *
     */
    
    public static int getMonth(Date date) {
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            
            return month+1;            
        }
        return 0;
    }
    public static int getLastDayOfMonth(Date date) {
        Calendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(date);
        return getLastDayOfMonth(calendarDate);
    }
    
    public static int getLastDayOfMonth(Calendar calendarDate) {
        Calendar returnDate = new GregorianCalendar();
        returnDate.setTime(calendarDate.getTime());

        int lastDay = returnDate.get(Calendar.DAY_OF_MONTH);
        returnDate.roll(Calendar.DAY_OF_MONTH, true);    
        while (lastDay < returnDate.get(Calendar.DAY_OF_MONTH)) {
            lastDay = returnDate.get(Calendar.DAY_OF_MONTH);
            returnDate.roll(Calendar.DAY_OF_MONTH, true);    
        }
        return lastDay;
    }
    
    public static int getYear(Date date) {
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            
            return year;            
        }
        return 0;
    }
    
    public static boolean sameMonthYear(Date date1, Date date2) {
        Assert.check(date1 != null, "date1 != null");            
        Assert.check(date2 != null, "date2 != null");            
        
        return (getMonth(date1) == getMonth(date2) && getYear(date1) == getYear(date2));
    }
    
    
    
    public static Date setYear(Date date, int newYear) {
        Assert.check(date != null, "date != null");            

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, newYear);
        
        return calendar.getTime();            
    }
    
    public static String formatDate(Date date, String format) {
        String formattedDate = null;
        if (date != null) {                                                      
            SimpleDateFormat  SimpleDateFormat = new SimpleDateFormat(format);
            formattedDate = SimpleDateFormat.format(date);
        }
        return formattedDate;
    }
    
    public static String formatDateOrEmpty(Date date, String format) {
        String formattedDate = StringUtil.nullToEmpty(formatDate(date,  format));
        return formattedDate;
    }
    
    public static String formatDateTime(Date date) {
        return formatDate(date, "MM-dd-yyyy HH:mm:ss");
    }
    
    public static Date getNow() {
        // TODO: why not just new Date().
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    
    public static Date normalizeYear(Date inDate) {        
        return normalizeYear(inDate, 10);
    }
    public static Date normalizeYear(Date inDate, int cutoff) {        
        Assert.check(cutoff > 0 && cutoff < 100, "cutoff > 0 && cutoff < 100");
        
        int year = getYear(inDate);
        if (year < 100) {
            if (year <= cutoff) {
                year = year + 2000;
            } else {
                year = year + 1900;
            }
        }
        
        return setYear(inDate, year);       
    }
    
    public static Date addDateAndTime(Date date, Date time) {
        Assert.check(date != null, "date != null");
        Assert.check(time != null, "time != null");
        
        Calendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(date);
        Calendar calendarTime = new GregorianCalendar();
        calendarTime.setTime(time);

        calendarDate.add(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
        calendarDate.add(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
        
        return calendarDate.getTime();        
    }
    public static String formatDaysHourMinutesFromMilliseconds(long milliseconds) {
        return formatDaysHourMinutesFromMilliseconds(milliseconds, "days ", "hrs ", "mins");
    }
    public static String formatDaysHourMinutesFromMillisecondsShort(long milliseconds) {
        return formatDaysHourMinutesFromMilliseconds(milliseconds, "d ", "h ", "m");
    }

    public static String formatDaysHourMinutesFromMilliseconds(long milliseconds, String daysString, String hrsString, String minsString) {
        // 24hours/day * 60min/hour * 60sec/min * 1000millsecond/sec = 360000 milliseconds/hour
        int MILLISECONDSPERDAY = 86400000;
        // 60min/hour * 60sec/min * 1000millsecond/sec = 360000 milliseconds/hour
        int MILLISECONDSPERHOUR = 3600000;
        // 60sec/min * 1000millsecond/sec = 360000 milliseconds/hour
        int MILLISECONDSPERMINUTE = 60000;
        
        long days = milliseconds / MILLISECONDSPERDAY;
        long remainder = milliseconds - (days * MILLISECONDSPERDAY);
        long hours = remainder / MILLISECONDSPERHOUR;
        remainder = remainder - (hours * MILLISECONDSPERHOUR);
        long minutes = remainder / MILLISECONDSPERMINUTE;

        StringBuffer strBuf = new StringBuffer();
        if (days != 0) {
            strBuf.append(days); 
            strBuf.append(daysString); 
            strBuf.append(hours); 
            strBuf.append(hrsString); 
            strBuf.append(minutes); 
            strBuf.append(minsString); 
        } else if (hours != 0) {
            strBuf.append(hours); 
            strBuf.append(hrsString); 
            strBuf.append(minutes); 
            strBuf.append(minsString); 
        } else {
            strBuf.append(minutes); 
            strBuf.append(minsString); 
        }
        
        return strBuf.toString();
    }
    
    public static String formatHourMinutesFromMilliseconds(long milliseconds) {
        // 60min/hour * 60sec/min * 1000millsecond/sec = 360000 milliseconds/hour
        int MILLISECONDSPERHOUR = 3600000;
        int MILLISECONDSPERMINUTE = 600000;
        
        long hours = milliseconds / MILLISECONDSPERHOUR;
        long minutes = (milliseconds - (hours * MILLISECONDSPERHOUR)) / MILLISECONDSPERMINUTE;
        
        StringBuffer strBuf = new StringBuffer();
        if (hours != 0) {
            strBuf.append(hours); 
            strBuf.append("hrs "); 
            strBuf.append(minutes); 
            strBuf.append("mins"); 
        } else {
            strBuf.append(minutes); 
            strBuf.append("mins"); 
        }
        
        return strBuf.toString();
    }
    // TODO: provide an input date.
    public static Date getBeginningOfYear() {
        return getBeginningOfYear(new Date());
    }

    public static Date getBeginningOfYear(Date date) {
        Calendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(date);
        int year = calendarDate.get(GregorianCalendar.YEAR);        

        GregorianCalendar beginningOfYear = new GregorianCalendar(year, Calendar.JANUARY, 1, 0, 0, 0);
        
        return beginningOfYear.getTime();
    }

    public static Date getEndOfYear() {
        return getEndOfYear(new Date());
    }

    public static Date getEndOfYear(Date date) {
        Calendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(date);
        int year = calendarDate.get(GregorianCalendar.YEAR);        

        GregorianCalendar endOfYear = new GregorianCalendar(year, Calendar.DECEMBER, 31, 23, 59, 59);
        
        return endOfYear.getTime();
    }

    public static Date getBeginningOfMonth(Date date) {
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        int year = startCalendar.get(GregorianCalendar.YEAR);        
        int month = startCalendar.get(GregorianCalendar.MONTH);        

        GregorianCalendar beginningOfMonth = new GregorianCalendar(year, month, 1, 0, 0, 0);
        
        return beginningOfMonth.getTime();
    }
    private Date getBeginningOfMonth() {    
        return getBeginningOfMonth(new Date());
    }

    public static Date getEndOfMonth(Date date) {
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        int year = startCalendar.get(GregorianCalendar.YEAR);        
        int month = startCalendar.get(GregorianCalendar.MONTH);
        int lastDayOfMonth = startCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);        

        GregorianCalendar endOfMonth = new GregorianCalendar(year, month, lastDayOfMonth, 23, 59, 59);
        
        return endOfMonth.getTime();
    }
    public static Date getBeginningOfDay(Date date) {
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        int year = startCalendar.get(GregorianCalendar.YEAR);        
        int month = startCalendar.get(GregorianCalendar.MONTH);        
        int day = startCalendar.get(GregorianCalendar.DAY_OF_MONTH);        

        GregorianCalendar beginningOfMonth = new GregorianCalendar(year, month, day, 0, 0, 0);
        
        return beginningOfMonth.getTime();
    }
    private Date getBeginningOfDay() {    
        return getBeginningOfMonth(new Date());
    }

    public static Date getEndOfDay(Date date) {
        GregorianCalendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(date);
        int year = startCalendar.get(GregorianCalendar.YEAR);        
        int month = startCalendar.get(GregorianCalendar.MONTH);        
        int day = startCalendar.get(GregorianCalendar.DAY_OF_MONTH);        

        GregorianCalendar beginningOfMonth = new GregorianCalendar(year, month, day, 23, 59, 59);
        
        return beginningOfMonth.getTime();
    }
    private Date getEndOfDay() {    
        return getBeginningOfMonth(new Date());
    }

    public static boolean sameDate(Date date1, Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        } else if (date1 == null && date2 != null) {
            return false;
        } else if (date1 != null && date2 == null) {
            return false;
        } else {
            String date1String = formatDate(date1, "MM/dd/yyyy");
            String date2String = formatDate(date2, "MM/dd/yyyy");
            return date1String.equals(date2String);
        }
    }
    public static Date getCurrentMonthYear() {
        String currentMonthString = DateUtil.formatDate(new Date(), "MM/yyyy");
        Date currentMonthYear = DateUtil.parseDate(currentMonthString, "MM/yyyy");
        return currentMonthYear;
    }
    public static Date getPreviousMonthYear(Date monthYear) {
        Calendar calendarDate = new GregorianCalendar();
        calendarDate.setTime(monthYear);
        
        calendarDate.add(Calendar.MONTH, -1);
        
        return calendarDate.getTime();
    }

    public static Date addDays(Date date, int days) {
        Assert.check(date != null, "date != null");
        
        Logger.debug(LOGGER_CATEGORY, "old time" + date.getTime());
        long time = date.getTime() + ((long)days * MILLISECONDS_PER_DAY);
        Logger.debug(LOGGER_CATEGORY, "new time" + time);
        
        return new Date(time);
    }

    public static Date addMinutes(Date date, int minutes) {
        Assert.check(date != null, "date != null");
        
        Logger.debug(LOGGER_CATEGORY, "old time" + date.getTime());
        long time = date.getTime() + ((long)minutes * MILLISECONDS_PER_MINUTE);
        Logger.debug(LOGGER_CATEGORY, "new time" + time);
        
        return new Date(time);
    }
    public static Date addSeconds(Date date, int second) {
        Assert.check(date != null, "date != null");
        //Logger.debug(this, "old time" + date.getTime());
        long time = date.getTime() + ((long)second * MILLISECONDS_PER_SECOND);
        //Logger.debug(this, "new time" + time);
        
        return new Date(time);
    }

    public static int getDifferenceDays(Date date1, Date date2) {
        Assert.check(date1 != null, "date1 != null");
        Assert.check(date2 != null, "date2 != null");
    	
        return (int)((date2.getTime() - date1.getTime()) / MILLISECONDS_PER_DAY);        
    }

    //from jsrservice
    private Date getDaysAgo(Date startDate, int numberDays) {    
        Logger.debug(this, "getDaysAgo1 $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        GregorianCalendar now = new GregorianCalendar();
        Logger.debug(this, "getDaysAgo1 " + now);
        now.add(GregorianCalendar.DAY_OF_YEAR, numberDays * -1);
        Logger.debug(this, "getDaysAgo2 " + now);
        return now.getTime();
    }
    
    private Date getDaysAgo(int numberDays) {    
        return getDaysAgo(new Date(), numberDays) ;
    }
    

}
