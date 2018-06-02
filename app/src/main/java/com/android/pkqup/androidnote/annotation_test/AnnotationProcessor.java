package com.android.pkqup.androidnote.annotation_test;

import java.lang.reflect.Constructor;

/**
 * Created by LiuCun on 2017/11/17.<br>
 * Describe
 */

public class AnnotationProcessor {

    public static void init(Object object) {

        if (!(object instanceof User)) {
            throw new IllegalArgumentException("[" + object.getClass().getSimpleName() + "] isn't type of User");
        }

        Constructor[] constructors = object.getClass().getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.isAnnotationPresent(UserMeta.class)) {
                UserMeta userFill = (UserMeta) constructor.getAnnotation(UserMeta.class);
                int age = userFill.age();
                int id = userFill.id();
                String name = userFill.name();
                ((User) object).setAge(age);
                ((User) object).setId(id);
                ((User) object).setName(name);
            }
        }
    }
}
