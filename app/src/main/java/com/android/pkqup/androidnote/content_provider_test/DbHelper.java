package com.android.pkqup.androidnote.content_provider_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class DbHelper extends SQLiteOpenHelper {

    // 数据库名
    private static final String DATABASE_NAME = "pkqup.db";
    // 数据库版本号
    private static final int DATABASE_VERSION = 1;

    // 表名
    public static final String USER_TABLE_NAME = "user";
    public static final String ADDRESS_TABLE_NAME = "address";
    public static final String SPECIAL_CHARACTER = "/#";

    //在构造方法中指定数据库的 数据库名 和 数据库版本号
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建两个表格:用户表 和地址表
        try {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + " (" + User.INDEX   + " INTEGER PRIMARY KEY AUTOINCREMENT," + User.NAME + " TEXT, " + User.USER_ID + " TEXT)");
            db.execSQL("CREATE TABLE IF NOT EXISTS " + ADDRESS_TABLE_NAME + " (" + Address.INDEX   + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ Address.NAME + " TEXT, " + Address.PHONE + " TEXT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            //数据库版本发生变化，删除旧表
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ADDRESS_TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
