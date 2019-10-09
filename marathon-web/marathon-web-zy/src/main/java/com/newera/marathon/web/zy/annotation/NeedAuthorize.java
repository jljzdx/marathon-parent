package com.newera.marathon.web.zy.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAuthorize {
    String value() default "";
}
