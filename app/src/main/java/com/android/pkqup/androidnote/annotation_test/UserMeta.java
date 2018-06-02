package com.android.pkqup.androidnote.annotation_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by LiuCun on 2017/11/17.<br>
 * Describe
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface UserMeta {
    public int id() default 0;

    public String name() default "";

    public int age() default 0;
}