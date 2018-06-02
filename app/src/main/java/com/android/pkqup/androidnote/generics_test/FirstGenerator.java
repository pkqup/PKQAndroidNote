package com.android.pkqup.androidnote.generics_test;

/**
 * Created by LiuCun on 2017/11/20.<br>
 * Describe
 */

/**
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，需将泛型的声明也一起加到类中
 * 即：class FirstGenerator<T> implements Generator<T>{
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，编译器会报错："Unknown class"
 */
public class FirstGenerator<T> implements Generator<T> {

    @Override
    public T makeSomeThing() {
        return null;
    }

}
