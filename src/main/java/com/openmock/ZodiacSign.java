package com.openmock;

import java.time.LocalDate;
import java.time.MonthDay;

public enum ZodiacSign {
    ARIES,
    TAURUS,
    GEMINI,
    CANCER,
    LEO,
    VIRGO,
    LIBRA,
    SCORPIO,
    SAGITTARIUS,
    CAPRICORN,
    AQUARIUS,
    PISCES;

    /**
     * Returns the correct zodiac sign for a given date.
     *
     * @param date The LocalDate object for which you want to get the zodiac sign.
     * @return The zodiac sign corresponding to the date.
     */
    public static ZodiacSign getZodiacSignFromDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can not be null");
        }

        MonthDay monthDay = MonthDay.of(date.getMonth(), date.getDayOfMonth());

        if (monthDay.isAfter(MonthDay.of(3, 20)) && monthDay.isBefore(MonthDay.of(4, 20))) {
            return ARIES;
        } else if (monthDay.isAfter(MonthDay.of(4, 19)) && monthDay.isBefore(MonthDay.of(5, 21))) {
            return TAURUS;
        } else if (monthDay.isAfter(MonthDay.of(5, 20)) && monthDay.isBefore(MonthDay.of(6, 21))) {
            return GEMINI;
        } else if (monthDay.isAfter(MonthDay.of(6, 20)) && monthDay.isBefore(MonthDay.of(7, 23))) {
            return CANCER;
        } else if (monthDay.isAfter(MonthDay.of(7, 22)) && monthDay.isBefore(MonthDay.of(8, 23))) {
            return LEO;
        } else if (monthDay.isAfter(MonthDay.of(8, 22)) && monthDay.isBefore(MonthDay.of(9, 23))) {
            return VIRGO;
        } else if (monthDay.isAfter(MonthDay.of(9, 22)) && monthDay.isBefore(MonthDay.of(10, 23))) {
            return LIBRA;
        } else if (monthDay.isAfter(MonthDay.of(10, 22)) && monthDay.isBefore(MonthDay.of(11, 22))) {
            return SCORPIO;
        } else if (monthDay.isAfter(MonthDay.of(11, 21)) && monthDay.isBefore(MonthDay.of(12, 22))) {
            return SAGITTARIUS;
        } else if (monthDay.isAfter(MonthDay.of(12, 21)) || monthDay.isBefore(MonthDay.of(1, 20))) {
            // Capricornio abarca parte de diciembre y parte de enero
            return CAPRICORN;
        } else if (monthDay.isAfter(MonthDay.of(1, 19)) && monthDay.isBefore(MonthDay.of(2, 19))) {
            return AQUARIUS;
        } else if (monthDay.isAfter(MonthDay.of(2, 18)) && monthDay.isBefore(MonthDay.of(3, 21))) {
            return PISCES;
        }
        // Este caso no debería alcanzarse con fechas válidas, pero se puede lanzar una excepción
        // o devolver un valor por defecto si se considera necesario.
        throw new IllegalArgumentException("The zodiac sign for the date could not be determined:" + date);
    }

}
