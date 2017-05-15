package com.wf2311.java8.time.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.wf2311.java8.time.converter.LocalDateConverter;
import com.wf2311.java8.time.converter.LocalDateTimeConverter;
import com.wf2311.java8.time.converter.LocalTimeConverter;
import com.wf2311.java8.time.properties.Java8TimePatternProperties;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <a href="https://gist.github.com/abop/4be6d8b3538c18f720484a783811278c">订制默认的 jackson mapper, 自定义 java 8 time api 中对象的序列化格式</a>
 * <a href="https://www.petrikainulainen.net/programming/spring-framework/spring-from-the-trenches-using-type-converters-with-spring-mvc/"> Using Type Converters With Spring MVC</a>
 *
 * @author wf2311
 * @time 2017/05/12 19:11.
 */
@Configuration
public class Java8TimeConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public Java8TimePatternProperties properties() {
        return new Java8TimePatternProperties();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.modules(
                new JavaTimeModule()
                        .addSerializer(LocalDate.class, new LocalDateSerializer(properties().dateFormatter()))
                        .addSerializer(LocalTime.class, new LocalTimeSerializer(properties().timeFormatter()))
                        .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(properties().dateTimeFormatter()))
                        .addDeserializer(LocalDate.class, new LocalDateDeserializer(properties().dateFormatter()))
                        .addDeserializer(LocalTime.class, new LocalTimeDeserializer(properties().timeFormatter()))
                        .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(properties().dateTimeFormatter()))
        );
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter(properties().getDate()));
        registry.addConverter(new LocalTimeConverter(properties().getTime()));
        registry.addConverter(new LocalDateTimeConverter(properties().getDateTime()));
    }
}
