package com.wf2311.java8.time.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.*;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.wf2311.java8.time.converter.*;
import com.wf2311.java8.time.properties.Java8TimePatternProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.*;

/**
 * <a href="https://gist.github.com/abop/4be6d8b3538c18f720484a783811278c">订制默认的 jackson mapper, 自定义 java 8 time api 中对象的序列化格式</a>
 * <a href="https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-using-type-converters-with-spring-mvc/"> Using Type Converters With Spring MVC</a>
 *
 * @author wf2311
 * @time 2017/05/12 19:11.
 */
@EnableConfigurationProperties(Java8TimePatternProperties.class)
public class Java8TimeConfiguration extends WebMvcConfigurerAdapter {
    private static final Logger log = LoggerFactory.getLogger(Java8TimeConfiguration.class);

    private Java8TimePatternProperties properties;

    public Java8TimeConfiguration(Java8TimePatternProperties properties) {
        this.properties = properties;
        log.info("using patterns with properties='{}'", properties);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
                new JavaTimeModule()
                        .addSerializer(LocalDate.class, new LocalDateSerializer(properties.dateFormatter()))
                        .addSerializer(LocalTime.class, new LocalTimeSerializer(properties.timeFormatter()))
                        .addSerializer(YearMonth.class, new YearMonthSerializer(properties.yearMonthFormatter()))
                        .addSerializer(MonthDay.class, new MonthDaySerializer(properties.monthDayFormatter()))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(properties.dateTimeFormatter()))
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(properties.dateFormatter()))
                        .addDeserializer(LocalTime.class, new LocalTimeDeserializer(properties.timeFormatter()))
                        .addDeserializer(YearMonth.class, new YearMonthDeserializer(properties.yearMonthFormatter()))
                        .addDeserializer(MonthDay.class, new MonthDayDeserializer(properties.monthDayFormatter()))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(properties.dateTimeFormatter()))
        );
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter(properties.getDate()));
        registry.addConverter(new LocalTimeConverter(properties.getTime()));
        registry.addConverter(new YearMonthConverter(properties.getYearMonth()));
        registry.addConverter(new MonthDayConverter(properties.getMonthDay()));
        registry.addConverter(new LocalDateTimeConverter(properties.getDateTime()));
    }
}
