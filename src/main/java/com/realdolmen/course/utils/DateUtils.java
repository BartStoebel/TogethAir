package com.realdolmen.course.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Class used to reformat dates
 */
public abstract class DateUtils {

    /**
     * Return a Date created from a string in a pattern
     * @param pattern
     * @return
     */
    public static Date createDate(String pattern) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(pattern);
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse date", e);
        }
    }

    /**
     * calculate how many years as between now and the date given
     * @param date
     * @return
     */
    public static long yearsFrom(Date date) {
        LocalDate d = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.YEARS.between(d, LocalDate.now());
    }

}
