package com.pkqup.aptlib.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiuCun on 2017/11/17.<br>
 * Describe
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Code {
    public String author();
    public String date() default "";
}
