package com.wf2311.java8.time.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * @author wf2311
 * @time 2017/05/12 20:36.
 */
@ConfigurationProperties(prefix = "java.time.pattern")
@Component
public class Java8TimePatternProperties {
    protected String dateTime = "yyyy-MM-dd HH:mm:ss";
    protected String date = "yyyy-MM-dd";
    protected String time = "HH:mm:ss";

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DateTimeFormatter dateTimeFormatter() {
        return formatter(dateTime);
    }

    public DateTimeFormatter timeFormatter() {
        return formatter(time);
    }

    public DateTimeFormatter dateFormatter() {
        return formatter(date);
    }

    private DateTimeFormatter formatter(String pattern) {
        if (pattern == null || pattern.trim().length() == 0) {
            return null;
        }
        return DateTimeFormatter.ofPattern(pattern);
    }
}
