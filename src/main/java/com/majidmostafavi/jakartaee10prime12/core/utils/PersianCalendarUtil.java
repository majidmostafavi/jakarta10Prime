package com.majidmostafavi.jakartaee10prime12.core.utils;

import com.majidmostafavi.jakartaee10prime12.core.utils.time.PersianMonth;
//import ir.ac.tums.utils.time.PersianMonth;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.TimeZone;

/**
 * Created by majid on 5/10/16.
 */
@ApplicationScoped
@Named
public class PersianCalendarUtil implements Serializable {

    public static String CONSTANT_Delimiter = "/";

    public static final double PERSIAN_EPOCH = 1948320.5;

    public static final double GREGORIAN_EPOCH = 1721425.5;
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String GREGORIAN_DATE_FORMAT = "yyyy/MM/dd";

    public static String toSolar(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
            return GregorianToSolar(sdf.format(date));
        }
        return "";
    }

    public static String toSolarText(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
            String[] str = GregorianToSolar(sdf.format(date)).split("/");
            StringBuilder builder = new StringBuilder();
            builder.append(TextUtils.numberToText(Double.parseDouble(str[2])) + " ");
            builder.append(PersianMonth.of(Integer.parseInt(str[1])) + " ");
            builder.append(TextUtils.numberToText(str[0]) + " ");
            return builder.toString();
        }
        return "";
    }

    public static PersianMonth toSolarMonth(Date date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
            String[] str = GregorianToSolar(sdf.format(date)).split("/");
           return PersianMonth.of(Integer.parseInt(str[1]));
        }
        throw new DateTimeException("Invalid value for MonthOfYear: ");
    }

    public static Date getBeginingOfYear(Date date) {
        String year = getSolarYear(date);
        if (year != null) {
            String solarStart = year + "/01/01";
            return toGregorian(solarStart);
        }
        return null;
    }

    public static String getCurrentSolarYear() {
        return getSolarYear(new Date());
    }

    public static String getSolarYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
        if (date != null) {
            String solar = GregorianToSolar(sdf.format(date));
            String year = solar.substring(0, 4);
            return year;
        }
        return null;
    }

    public static String getSolarDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
        if (date != null) {
            String solar = GregorianToSolar(sdf.format(date));
            String day = solar.substring(8,10);
            return day;
        }
        return null;
    }

    public static String getSolar(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
        if (date != null) {
            String solar = GregorianToSolar(sdf.format(date));
            String year = solar.substring(0, 4) + solar.substring(5, 7) + solar.substring(8, 10) ;
            return year;
        }
        return null;
    }

    public static Date getEndOfYear(Date date) {
        String year = getSolarYear(date);
        if (year != null) {
            String solarStart = year + "/12/29";
            return toGregorian(solarStart);
        }
        return null;
    }


    public static Date toGregorian(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
        try {
            String d = SolarToGregorian(date);
            Date parsedDate = simpleDateFormat.parse(d);
            return parsedDate;
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date toGMT(Date input){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            return sdf.parse(sdf.format(input));
        } catch (ParseException e) {
            return null;
        }


//        Calendar cal = Calendar.getInstance();
//        cal.setTime(input);
//
//// Set time fields to zero
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//
//// Put it back in the Date object
//        Date output = new Date(input.getYear(),input.getMonth(),input.getDate(),0,0,0);
//        return output;
    }

    public static Date toSolar(String date) {
        try {
            date = date.replaceAll("-","/");
            if(dateValidation(date)) {
                Date parsedDate = new Date(date);
                return parsedDate;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    public static boolean dateValidation(String dateStr){
        dateStr = formatDateStr(dateStr);
        if(dateStr.matches( "^\\d{4}/\\d{2}/\\d{2}$")) {
            return dateStr.matches("^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))/02/29)$"
                    + "|^(((19|2[0-9])[0-9]{2})/02/(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})/(0[13578]|10|12)/(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})/(0[469]|11)/(0[1-9]|[12][0-9]|30))$");
        }
        return false;
    }

    private static String formatDateStr(String dateStr){
        StringBuilder builder = new StringBuilder(dateStr);
        if (dateStr.matches( "^\\d{4}/\\d{1}/\\d{1}$")){
            String replaceFValue = "0"+builder.charAt(5);
            builder = builder.replace(5,6,replaceFValue);
            String replaceSValue = "0"+builder.charAt(8);
            builder = builder.replace(8,9,replaceSValue);
        }else if (dateStr.matches( "^\\d{4}/\\d{1}/\\d{2}$")){
            String replaceFValue = "0"+builder.charAt(5);
            builder = builder.replace(5,6,replaceFValue);
        }else if (dateStr.matches( "^\\d{4}/\\d{2}/\\d{1}$")){
            String replaceSValue = "0"+builder.charAt(8);
            builder = builder.replace(8,9,replaceSValue);
        }
        return builder.toString();
    }

    public static Calendar toGregorianCalendar(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(GREGORIAN_DATE_FORMAT);
        try {
            String d = SolarToGregorian(date);
            Date parsedDate = simpleDateFormat.parse(d);
            Calendar c = Calendar.getInstance();
            c.setTime(parsedDate);
            return c;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param solarDateAsTimeStamp .toString()
     * @return gregorian date format e.g 2008-03-31 or this method customize for
     * adempiere
     */
    public static String SolarToGregorian(String solarDateAsTimeStamp) {
        if (solarDateAsTimeStamp.indexOf("-") != -1) {
            CONSTANT_Delimiter = "-";
        } else {
            CONSTANT_Delimiter = "/";
        }
        if (solarDateAsTimeStamp.length() > 0) {
            String[] dateElement = solarDateAsTimeStamp.toString().split(
                    CONSTANT_Delimiter);
            //
            int m_currentYear = Integer.parseInt(dateElement[0]);
            int m_currentMonth = Integer.parseInt(dateElement[1]);
            String[] daywithtime = dateElement[2].split(" ");
            int m_currentDay = Integer.parseInt(daywithtime[0]);

            // in some date format like 08 year is less than 1387
            if (m_currentYear < 1300)
                m_currentYear += 1300;

            String day, month, year;

            day = Integer.toString(m_currentDay);
            month = Integer.toString(m_currentMonth);
            year = Integer.toString(m_currentYear);

            if (m_currentDay < 10)
                day = "0" + day;
            if (m_currentMonth < 10)
                month = "0" + month;

            if (daywithtime.length > 2) {
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1] + " " + daywithtime[2];
                /**
                 * SimpleDateFormat retValue =
                 * (SimpleDateFormat)DateFormat.getDateTimeInstance
                 * (DateFormat.SHORT, DateFormat.SHORT, m_locale);
                 */
                solarDateAsTimeStamp = changeFormat(solarDateAsTimeStamp) + " "
                        + daywithtime[1] + " " + daywithtime[2];
            } else if (daywithtime.length > 1 && daywithtime.length < 3)
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1];
            else
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay));


        }

        return solarDateAsTimeStamp;

    }

    public static String StringToSolar(String solarDateAsTimeStamp) {
        if (solarDateAsTimeStamp.indexOf("-") != -1) {
            CONSTANT_Delimiter = "-";
        } else {
            CONSTANT_Delimiter = "/";
        }
        if (solarDateAsTimeStamp.length() > 0) {
            String[] dateElement = solarDateAsTimeStamp.toString().split(
                    CONSTANT_Delimiter);
            //
            int m_currentYear = Integer.parseInt(dateElement[0]);
            int m_currentMonth = Integer.parseInt(dateElement[1]);
            String[] daywithtime = dateElement[2].split(" ");
            int m_currentDay = Integer.parseInt(daywithtime[0]);

            // in some date format like 08 year is less than 1387
            if (m_currentYear < 1900)
                m_currentYear += 2050;

            String day, month, year;

            day = Integer.toString(m_currentDay);
            month = Integer.toString(m_currentMonth);
            year = Integer.toString(m_currentYear);

            if (m_currentDay < 10)
                day = "0" + day;
            if (m_currentMonth < 10)
                month = "0" + month;

            if (daywithtime.length > 2) {
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1] + " " + daywithtime[2];
                /**
                 * SimpleDateFormat retValue =
                 * (SimpleDateFormat)DateFormat.getDateTimeInstance
                 * (DateFormat.SHORT, DateFormat.SHORT, m_locale);
                 */
                solarDateAsTimeStamp = changeFormat(solarDateAsTimeStamp) + " "
                        + daywithtime[1] + " " + daywithtime[2];
            } else if (daywithtime.length > 1 && daywithtime.length < 3)
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1];
            else
                solarDateAsTimeStamp = convertJdnToGregorian(convertPersianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay));


        }

        return solarDateAsTimeStamp;

    }

    /**
     * @param gregorianDateAsTimeStamp .toString() format supported 08/07/17 10:00 AM, 2008/07/17,
     *                                 2008-03-31 12:59:00.00, 2008-07-17 this is main method of
     *                                 convertion other methods use this
     * @return solar date format e.g 1387-01-01
     */
    public static String GregorianToSolar(String gregorianDateAsTimeStamp) {

        if (gregorianDateAsTimeStamp.indexOf("-") != -1) {
            CONSTANT_Delimiter = "-";
        } else {
            CONSTANT_Delimiter = "/";
        }
        if (gregorianDateAsTimeStamp.length() > 0) {
            String[] dateElement = gregorianDateAsTimeStamp.toString().split(
                    CONSTANT_Delimiter);
            int m_currentYear = Integer.parseInt(dateElement[0]);

            int m_currentMonth = Integer.parseInt(dateElement[1]);
            String[] daywithtime = dateElement[2].split(" ");
            int m_currentDay = Integer.parseInt(daywithtime[0]);

            /**
             * this check is for short simple date format 7/25/08 10:00 AM after
             * format fix we don not need to use this in solar method again
             * */
            if (daywithtime.length > 2) {
                m_currentYear = Integer.parseInt(daywithtime[0]);
                m_currentMonth = Integer.parseInt(dateElement[0]);
                m_currentDay = Integer.parseInt(dateElement[1]);
            }

//			if (m_currentYear < 2000)
//				m_currentYear += 2000;

            String day, month, year;

            day = Integer.toString(m_currentDay);
            month = Integer.toString(m_currentMonth);
            year = Integer.toString(m_currentYear);

            if (m_currentDay < 10)
                day = "0" + day;
            if (m_currentMonth < 10)
                month = "0" + month;

            /** usin supprot for this format 7/25/08 10:00 AM */
            if (daywithtime.length > 2)
                gregorianDateAsTimeStamp = convertJdnToPersian(convertGregorianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1] + " " + daywithtime[2];
            /** 2008-03-31 12:59:00.00 */
            else if (daywithtime.length > 1 && daywithtime.length < 3)
                gregorianDateAsTimeStamp = convertJdnToPersian(convertGregorianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay))
                        + " " + daywithtime[1];
            /** 2008-07-17, 2008/07/17 */
            else
                gregorianDateAsTimeStamp = convertJdnToPersian(convertGregorianToJdn(
                        m_currentYear, m_currentMonth, m_currentDay));


        }

        return gregorianDateAsTimeStamp;

    }

    /**
     * @param gYear
     * @param gMonth
     * @param gDay
     * @return
     */
    public static String GregorianToSolar(int gYear, int gMonth, int gDay) {
        String compondDate = String.valueOf(gYear) + CONSTANT_Delimiter
                + String.valueOf(gMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(gDay);
        return GregorianToSolar(compondDate);
    }

    /**
     * @param gregorianDate as timestamp.toString()
     * @return
     */
    public static int getSolarYear(String gregorianDate) {
        int solarYear = 0;
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarYear = Integer.parseInt(spliterDate[0]);
        return solarYear;
    }

    /**
     * @param gYear
     * @param gMonth
     * @param gDay
     * @return
     */
    public static int getSolarYear(int gYear, int gMonth, int gDay) {
        int solarYear = 0;
        String gregorianDate = String.valueOf(gYear) + CONSTANT_Delimiter
                + String.valueOf(gMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(gDay);
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarYear = Integer.parseInt(spliterDate[0]);
        return solarYear;
    }

    /**
     * @param gregorianDate as timestamp.toString()
     * @return
     */
    public static int getSolarMonth(String gregorianDate) {
        int solarMonth = 0;
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarMonth = Integer.parseInt(spliterDate[1]);
        return solarMonth;
    }

    /**
     * @param gYear
     * @param gMonth
     * @param gDay
     * @return
     */
    public static int getSolarMonth(int gYear, int gMonth, int gDay) {
        int solarMonth = 0;
        String gregorianDate = String.valueOf(gYear) + CONSTANT_Delimiter
                + String.valueOf(gMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(gDay);
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarMonth = Integer.parseInt(spliterDate[1]);
        return solarMonth;
    }

    /**
     * @param gregorianDate as timestamp.toString()
     * @return
     */
    public static int getSolarDay(String gregorianDate) {
        int solarDay = 0;
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarDay = Integer.parseInt(spliterDate[2]);
        return solarDay;
    }

    /**
     * @param gYear
     * @param gMonth
     * @param gDay
     * @return
     */
    public static int getSolarDay(int gYear, int gMonth, int gDay) {
        int solarDay = 0;
        String gregorianDate = String.valueOf(gYear) + CONSTANT_Delimiter
                + String.valueOf(gMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(gDay);
        String[] spliterDate = GregorianToSolar(gregorianDate).split(
                CONSTANT_Delimiter);
        solarDay = Integer.parseInt(spliterDate[2]);
        return solarDay;
    }

    /**
     * @param sYear
     * @param sMonth
     * @param sDay
     * @return only return gregorian year
     */
    public static int getGregorianYear(int sYear, int sMonth, int sDay) {
        int gregorianYear = 0;
        String solarDate = String.valueOf(sYear) + CONSTANT_Delimiter
                + String.valueOf(sMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(sDay);
        String[] spliterDate = SolarToGregorian(solarDate).split(
                CONSTANT_Delimiter);
        gregorianYear = Integer.parseInt(spliterDate[0]);
        return gregorianYear;
    }

    /**
     * @param sYear
     * @param sMonth
     * @param sDay
     * @return only return gregorian Month
     */
    public static int getGregorianMonth(int sYear, int sMonth, int sDay) {
        int gregorianMonth = 0;
        String solarDate = String.valueOf(sYear) + CONSTANT_Delimiter
                + String.valueOf(sMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(sDay);
        String[] spliterDate = SolarToGregorian(solarDate).split(
                CONSTANT_Delimiter);
        gregorianMonth = Integer.parseInt(spliterDate[1]);
        return gregorianMonth;
    }

    /**
     * @param sYear
     * @param sMonth
     * @param sDay
     * @return only return gregorian day
     */
    public static int getGregorianDay(int sYear, int sMonth, int sDay) {
        int gregorianDay = 0;
        String solarDate = String.valueOf(sYear) + CONSTANT_Delimiter
                + String.valueOf(sMonth).concat(CONSTANT_Delimiter)
                + String.valueOf(sDay);
        String[] spliterDate = SolarToGregorian(solarDate).split(
                CONSTANT_Delimiter);
        gregorianDay = Integer.parseInt(spliterDate[2]);
        return gregorianDay;
    }

    public static boolean Leap_Persian(int year) {
        return ((((((year - ((year > 0) ? 474 : 473)) % 2820) + 474) + 38) * 682) % 2816) < 682;
    }

    // LEAP_GREGORIAN -- Is a given year in the Gregorian calendar a leap year ?
    public static boolean Leap_Gregorian(double year) {
        return ((year % 4) == 0)
                && (!(((year % 100) == 0) && ((year % 400) != 0)));
    }

    /**
     * Convert to JDN
     */

// GREGORIAN_TO_JD -- Determine Julian day number from Gregorian calendar
// date
    public static double convertGregorianToJdn(double year, double month, int day) {
        return (GREGORIAN_EPOCH - 1)
                + (365 * (year - 1))
                + Math.floor((year - 1) / 4)
                + (-Math.floor((year - 1) / 100))
                + Math.floor((year - 1) / 400)
                + Math.floor((((367 * month) - 362) / 12)
                + ((month <= 2) ? 0 : (Leap_Gregorian(year) ? -1 : -2))
                + day);
    }

    // JULIAN_TO_JD -- Determine Julian day number from Julian calendar date
    public static double convertJulianToJdn(int year, int month, int day) {
    /* Adjust negative common era years to the zero-based notation we use. */

        if (year < 1) {
            year++;
        }

    /*
       * Algorithm as given in Meeus, Astronomical Algorithms, Chapter 7, page
       * 61
       */

        if (month <= 2) {
            year--;
            month += 12;
        }

        return ((Math.floor((365.25 * (year + 4716)))
                + Math.floor((30.6001 * (month + 1))) + day) - 1524.5);
    }

    // PERSIAN_TO_JD -- Determine Julian day from Persian date
    public static double convertPersianToJdn(double year, double month, double day) {
        double epbase, epyear;

        epbase = year - ((year >= 0) ? 474 : 473);
        epyear = 474 + mod(epbase, 2820);

        return day
                + ((month <= 7) ? ((month - 1) * 31) : (((month - 1) * 30) + 6))
                + Math.floor(((epyear * 682) - 110) / 2816) + (epyear - 1)
                * 365 + Math.floor(epbase / 2820) * 1029983
                + (PERSIAN_EPOCH - 1);
    }

/** JDN Converts to other */
    /**
     * @param jd
     * @return
     */
    public static String convertJdnToPersian(double jd) {
        double year, month, day, depoch, cycle, cyear, ycycle, aux1, aux2, yday;

        jd = Math.floor(jd) + 0.5;

        depoch = jd - convertPersianToJdn(475, 1, 1);
        cycle = Math.floor(depoch / 1029983);
        cyear = mod(depoch, 1029983);
        if (cyear == 1029982) {
            ycycle = 2820;
        } else {
            aux1 = Math.floor(cyear / 366);
            aux2 = mod(cyear, 366);
            ycycle = Math
                    .floor(((2134 * aux1) + (2816 * aux2) + 2815) / 1028522)
                    + aux1 + 1;
        }
        year = ycycle + (2820 * cycle) + 474;
        if (year <= 0) {
            year--;
        }
        yday = (jd - convertPersianToJdn(year, 1, 1)) + 1;
        month = (yday <= 186) ? Math.ceil(yday / 31) : Math
                .ceil((yday - 6) / 30);
        day = (jd - convertPersianToJdn(year, month, 1)) + 1;
        String strYear = Integer.toString((int) year), strMonth = Integer
                .toString((int) month), strDay = Integer.toString((int) day);

        if (strMonth.length() < 2)
            strMonth = "0" + strMonth;
        if (strDay.length() < 2)
            strDay = "0" + strDay;

        return strYear + CONSTANT_Delimiter + strMonth + CONSTANT_Delimiter
                + strDay;
    }

    /**
     * @param jd
     * @return
     */
    public static String convertJdnToGregorian(double jd) {
        double wjd, depoch, quadricent, dqc, cent, dcent, quad, dquad, yindex, dyindex, year, yearday, leapadj;

        wjd = Math.floor(jd - 0.5) + 0.5;
        depoch = wjd - GREGORIAN_EPOCH;
        quadricent = Math.floor(depoch / 146097);
        dqc = mod(depoch, 146097);
        cent = Math.floor(dqc / 36524);
        dcent = mod(dqc, 36524);
        quad = Math.floor(dcent / 1461);
        dquad = mod(dcent, 1461);
        yindex = Math.floor(dquad / 365);
        year = (quadricent * 400) + (cent * 100) + (quad * 4) + yindex;
        if (!((cent == 4) || (yindex == 4))) {
            year++;
        }
        yearday = wjd - convertGregorianToJdn(year, 1, 1);
        leapadj = ((wjd < convertGregorianToJdn(year, 3, 1)) ? 0
                : (Leap_Gregorian(year) ? 1 : 2));
        double month = Math.floor((((yearday + leapadj) * 12) + 373) / 367);
        double day = (wjd - convertGregorianToJdn(year, month, 1)) + 1;

        String strYear = Integer.toString((int) year), strMonth = Integer
                .toString((int) month), strDay = Integer.toString((int) day);

        if (strMonth.length() < 2)
            strMonth = "0" + strMonth;
        if (strDay.length() < 2)
            strDay = "0" + strDay;

        return strYear + CONSTANT_Delimiter + strMonth + CONSTANT_Delimiter
                + strDay;
    }

    /**
     * @param jdn
     * @return
     */
    public static String convertJdnToJulian(double jdn) {
        double z, a, alpha, b, c, d, e, year, month, day, td = 0;

        td += 0.5;
        z = Math.floor(td);

        a = z;
        b = a + 1524;
        c = Math.floor((b - 122.1) / 365.25);
        d = Math.floor(365.25 * c);
        e = Math.floor((b - d) / 30.6001);

        month = Math.floor((e < 14) ? (e - 1) : (e - 13));
        year = Math.floor((month > 2) ? (c - 4716) : (c - 4715));
        day = b - d - Math.floor(30.6001 * e);

    /*
       * If year is less than 1, subtract one to convert from a zero based
       * date system to the common era system in which the year -1 (1 B.C.E)
       * is followed by year 1 (1 C.E.).
       */

        if (year < 1) {
            year--;
        }
        String strYear = Integer.toString((int) year), strMonth = Integer
                .toString((int) month), strDay = Integer.toString((int) day);

        if (strMonth.length() < 2)
            strMonth = "0" + strMonth;
        if (strDay.length() < 2)
            strDay = "0" + strDay;

        return strYear + CONSTANT_Delimiter + strMonth + CONSTANT_Delimiter
                + strDay;
    }

    public static double mod(double args1, double args2) {
        return args1 % args2;
    }

    /**
     * @param Date input as 1387-04-26 7:59 AM use in solartogregorian method to
     *             change format to 7/16/08 7:59 AM
     * @return gregorian date format as 7/16/08 7:59 AM
     */
    public static String changeFormat(String Date) {
        String[] fixFormat = Date.split(CONSTANT_Delimiter);
        String newMonth = fixFormat[1].substring(1);
        String newDay = fixFormat[2].substring(0, fixFormat[2].indexOf(" "));
        String newYear = fixFormat[0].substring(2);
        return newMonth + "/" + newDay + "/" + newYear;
    }


    /**
     * return current hour/minute in hour:minute format
     *
     * @return
     */
    public static String currentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getTime(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            return simpleDateFormat.format(calendar.getTime());
        }
        return "";
    }

    public static String currentTimeSecond() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getCurrentSolarDate() {
        return toSolar(new Date());
    }

    public static Date getNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
        return c.getTime();
    }

    public static Date getManyNextDay(Date date,int numberOfDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + numberOfDate);
        return c.getTime();
    }


    public static Date getPrevDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 1);
        return c.getTime();
    }

    public static Date getPrevSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) - 1);
        return c.getTime();
    }

    public static int compareDatePart(Date firstDate, Date secondDate) {
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);
        firstCalendar.set(Calendar.HOUR_OF_DAY, 0);
        firstCalendar.set(Calendar.MINUTE, 0);
        firstCalendar.set(Calendar.SECOND, 0);
        firstCalendar.set(Calendar.MILLISECOND, 0);

        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);
        secondCalendar.set(Calendar.HOUR_OF_DAY, 0);
        secondCalendar.set(Calendar.MINUTE, 0);
        secondCalendar.set(Calendar.SECOND, 0);
        secondCalendar.set(Calendar.MILLISECOND, 0);
        int result = firstCalendar.compareTo(secondCalendar);
        return result;
    }

    public static Date getThreeNextDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 3);
        return c.getTime();
    }

    public static boolean dateBetween(Date sDate,Date eDate,Date targetDate){
        return targetDate.after(sDate) && targetDate.before(eDate) ? true : false;
    }

    public static Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public static Date addMinuteToJavaUtilDate(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static Period monthBetweenDate(Date sDate,Date eDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        sDate = addHoursToJavaUtilDate(sDate,1);
        Instant instantStartDate = sDate.toInstant();
        eDate =addHoursToJavaUtilDate(eDate,1);
        Instant instantEndDate = eDate.toInstant();

        LocalDate fromDate = instantStartDate.atZone(defaultZoneId).toLocalDate();
        LocalDate toDate = instantEndDate.atZone(defaultZoneId).toLocalDate();
        Period intervalPeriod = Period.between(fromDate, toDate);
        return intervalPeriod;
    }

    public static int monthBetweenDateINT(Date sDate,Date eDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        sDate = addHoursToJavaUtilDate(sDate,1);
        Instant instantStartDate = sDate.toInstant();
        eDate =addHoursToJavaUtilDate(eDate,1);
        Instant instantEndDate = eDate.toInstant();

        LocalDate fromDate = instantStartDate.atZone(defaultZoneId).toLocalDate();
        LocalDate toDate = instantEndDate.atZone(defaultZoneId).toLocalDate();
        Period intervalPeriod = Period.between(fromDate, toDate);
        return intervalPeriod.getMonths();
    }

    public static int yearBetweenDate(Date sDate,Date eDate){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        sDate = addHoursToJavaUtilDate(sDate,1);
        Instant instantStartDate = sDate.toInstant();
        eDate =addHoursToJavaUtilDate(eDate,1);
        Instant instantEndDate = eDate.toInstant();

        LocalDate fromDate = instantStartDate.atZone(defaultZoneId).toLocalDate();
        LocalDate toDate = instantEndDate.atZone(defaultZoneId).toLocalDate();
        Period intervalPeriod = Period.between(fromDate, toDate);
        return intervalPeriod.getYears();
    }

    public static int yearBetweenDate2(Date sDate,Date eDate){
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(sDate);
        int sYear = PersianCalendarUtil.getSolarYear(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH)+1, currentCal.get(Calendar.DAY_OF_MONTH));
        currentCal.setTime(eDate);
        int eYear = PersianCalendarUtil.getSolarYear(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH)+1, currentCal.get(Calendar.DAY_OF_MONTH));
        return eYear - sYear;
    }

    public static int monthBetweenDate2(Date sDate,Date eDate){
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(sDate);
        int sMonth = PersianCalendarUtil.getSolarMonth(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH)+1, currentCal.get(Calendar.DAY_OF_MONTH));
        currentCal.setTime(eDate);
        int eMonth = PersianCalendarUtil.getSolarMonth(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH)+1, currentCal.get(Calendar.DAY_OF_MONTH));
        return eMonth - sMonth;
    }

    public static Date getDateWithoutTimeUsingFormat(Date date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(date));
    }

    public static Date addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public static String formatDate(Date date, String format) {
        Formatter fmt = new Formatter();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //help: String format -> refer to https://docs.oracle.com/javase/7/docs/api/java/util/Formatter.html  Section: Date/Time Conversions
        String convertedDate = fmt.format(format, cal, cal, cal).toString();
        fmt.close();

        return convertedDate;
    }

    public boolean depreciationDateValidation(Date date) throws Exception{
        try {
            String day = getSolarDay(date);
            //calculate depreciation for current month and next month (when true)
            return Integer.parseInt(day) <= 15;
        }catch (Exception e){
            throw new Exception();
        }
    }

}