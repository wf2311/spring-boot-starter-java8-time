package com.wf2311.java8.time.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * @author wf2311
 * @time 2017/05/15 09:50.
 */
public final class YearMonthConverter implements Converter<String, YearMonth> {

    private final DateTimeFormatter formatter;

    public YearMonthConverter(String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public YearMonth convert(String source) {
        if (source == null || source.isEmpty()) {
            return null;
        }

        return YearMonth.parse(source, formatter);
    }
}