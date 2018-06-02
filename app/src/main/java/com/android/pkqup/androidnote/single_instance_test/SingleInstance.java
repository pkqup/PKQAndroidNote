package com.android.pkqup.androidnote.single_instance_test;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe 单例模式的几种写法
 */

public class SingleInstance {


    private static SingleInstance instance;

    // ----------------------双重校验锁（推荐这种写法）----------------------//
    private volatile static SingleInstance singleton;// 私有化其实例

    private SingleInstance() {}// 私有化构造方法

    public static SingleInstance getSingleton() {
        if (singleton == null) {
            synchronized (SingleInstance.class) {
                if (singleton == null) {
                    singleton = new SingleInstance();
                }
            }
        }
        return singleton;
    }
    // ----------------------双重校验锁（推荐这种写法）----------------------//



    // ----------------------懒汉式，线程不安全----------------------//
    public static SingleInstance getInstance() {
        if (instance == null) {
            instance = new SingleInstance();
        }
        return instance;
    }
    // ----------------------懒汉式，线程不安全---------------------//



    // ----------------------懒汉式，线程安全----------------------//
    // public static synchronized SingleInstance getInstanceTwo() {
    // if (instance == null) {
    // instance = new SingleInstance();
    // }
    // return instance;
    // }
    // ----------------------懒汉式，线程安全----------------------//



    // ----------------------饿汉式----------------------//
    // private static SingleInstance instanceThree = new SingleInstance();
    //
    // public static SingleInstance getInstanceThree() {
    // return instanceThree;
    // }
    // ----------------------饿汉式----------------------//



}
