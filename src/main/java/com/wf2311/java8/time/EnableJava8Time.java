package com.wf2311.java8.time;

import com.wf2311.java8.time.configuration.Java8TimeConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author wf2311
 * @time 2017/05/15 13:05.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({Java8TimeConfiguration.class})
public @interface EnableJava8Time {
}

