package com.openmock.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilTest {


    private int subStringToInt(String str, int init, int end) {
        return Integer.parseInt(str.substring(init, end));
    }

    @Test
    public void nowToUTC() {
        Calendar now = GregorianCalendar.getInstance();

        String dateStr = DateUtil.nowToUTC();

        assertNotNull(dateStr);
        assertEquals(now.get(Calendar.YEAR), subStringToInt(dateStr, 0, 4));
        assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(dateStr, 5, 7));
        assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(dateStr, 8, 10));
    }

    @Test
    public void utcToDate() {
        final String utcRef = "2020-05-21T16:30:52Z";

        try {
            Date utc = DateUtil.utcToDate(utcRef);
            assertEquals(utcRef, DateUtil.dateToUTC(utc));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void dateToUTC() {
        Calendar now = GregorianCalendar.getInstance();
        String nowStr = DateUtil.dateToUTC(now.getTime());

        assertNotNull(nowStr);
        assertEquals(now.get(Calendar.YEAR), subStringToInt(nowStr, 0, 4));
        assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(nowStr, 5, 7));
        assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(nowStr, 8, 10));
    }

    @Test
    public void strToDate() {
        final String strDate = "01/12/2020";
        final String format = "dd/MM/yyyy";

        try {
            Date date = DateUtil.strToDate(strDate, format);
            assertEquals("Tue Dec 01 00:00:00 GMT 2020",
                    date.toString()
                            .replace("WET", "GMT")
                            .replace("CET", "GMT")
                            .replace("CST", "GMT"));
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void strWithLocaleToDate() {
        final String strDate = "June 22, 2022";
        final String format = "MMM d, yyyy";

        try {
            Date date = DateUtil.strToDate(strDate, Locale.ENGLISH, format);
            assertEquals("Wed Jun 22 00:00:00 WEST 2022", date.toString());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void strEngToDate() {
        final String strDate = "June 22, 2022";
        final String format = "MMM d, yyyy";

        try {
            Date date = DateUtil.strEngToDate(strDate, format);
            assertEquals("Wed Jun 22 00:00:00 WEST 2022", date.toString());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void spaEngToDate() {
        final String strDate = "22 de agosto del 2024";

        try {
            Date date = DateUtil.strSpaToDate(strDate, DateUtil.SPANISH_DATE_FORMAT);
            assertEquals("Thu Aug 22 00:00:00 WEST 2024", date.toString());
        } catch (ParseException e) {
            fail(e.getMessage());
        }
    }
}