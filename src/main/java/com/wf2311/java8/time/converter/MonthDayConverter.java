package com.wf2311.java8.time.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * @author wf2311
 * @time 2017/05/15 09:50.
 */
public final class MonthDayConverter implements Converter<String, MonthDay> {

    private final DateTimeFormatter formatter;

    public MonthDayConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public MonthDay convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        return MonthDay.parse(source, formatter);
    }
}
