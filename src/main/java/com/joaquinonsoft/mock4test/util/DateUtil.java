package com.joaquinonsoft.mock4test.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static final String UTC_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String SPANISH_DATE_FORMAT = "dd 'de' MMMM 'del' yyyy";

    public static LocalDate now() {
        return LocalDate.now();
    }

    public static LocalDate nowXYearsAgo(int numYears) {
        return LocalDate.now().minusYears(numYears);
    }

    public static YearMonth nowYearMonth() {
        return YearMonth.now();
    }

    public static YearMonth nowYearMonthXYearsAgo(int numYears) {
        return YearMonth.now().minusYears(numYears);
    }

    /**
     * Return current time in UTC format, e.g.
     * 2020-05-21T16:30:52.123Z
     * @return current time in UTC format
     */
    public static String nowToUTC() {
        return Instant.now().toString() ;
    }

    public static String dateToUTC(Date d) {
        DateFormat dateFormat = new SimpleDateFormat(UTC_DATE_FORMAT);
        return dateFormat.format(d);
    }

    /**
     * Generate a Date object from a string in UTC format
     * @param utc - String which contains a date in UTC format, e.g.
     * "2020-05-21T16:30:52.123Z"
     * @return Date object from a string in UTC format
     * @throws ParseException
     */
    public static Date utcToDate(String utc) throws ParseException {
        return new SimpleDateFormat(UTC_DATE_FORMAT).parse(utc);
    }

    public static Date strToDate(String strDate, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(strDate);
    }

    public static Date strEngToDate(String strDate, String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.ENGLISH).parse(strDate);
    }

    public static Date strSpaToDate(String strDate, String format) throws ParseException {
        return new SimpleDateFormat(format, Locale.forLanguageTag("es-ES")).parse(strDate);
    }

    public static Date strToDate(String strDate, Locale locale, String format) throws ParseException {
        return new SimpleDateFormat(format, locale).parse(strDate);
    }

    /**
     * Get current time stamp
     * @return current time stamp
     */
    public static long getTimestamp() {
        Timestamp ts = Timestamp.from(Instant.now());
        return ts.getTime();
    }
}