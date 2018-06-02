package com.android.pkqup.androidnote.content_provider_test;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class MyContentProvider extends ContentProvider {

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private ContentResolver resolver;

    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher uriMatcher;

    // 用户定义列名->数据库列名的映射
    private static final HashMap<String, String> userHashMap;
    private static final HashMap<String, String> addressHashMap;


    // 定义ContentProvider的授权信息，即唯一标识
    public static final String AUTHORITY = "com.pkqup.android.note";

    // 定义Uri匹配返回码
    public static final int USER_CODE = 1;
    public static final int USER_CODE_SINGLE = 2;
    public static final int ADDRESS_CODE = 3;
    public static final int ADDRESS_CODE_SINGLE = 4;

    // 设置URI
    // Uri uri = Uri.parse("content://com.carson.provider/User/1")
    // 上述URI指向的资源是：名为 com.carson.provider 的 ContentProvider  中表名 为`User` 中的 `id`为1的数据

    // 特别注意：URI模式存在匹配通配符* 和 ＃

    // *：匹配任意长度的任何有效字符的字符串
    // 以下的URI 表示 匹配provider的任何内容
    // content://com.example.app.provider/*

    // ＃：匹配任意长度的数字字符的字符串
    // 以下的URI 表示 匹配provider中的table表的所有行
    // content://com.example.app.provider/table/#



    // 在静态代码块中初始化 UriMatcher
    static {
        // 初始化 UriMatcher
        // 常量UriMatcher.NO_MATCH ，不匹配任何路径的返回码 即初始化时不匹配任何东西
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // 在ContentProvider 中注册URI（即addURI()）
        uriMatcher.addURI(AUTHORITY, DbHelper.USER_TABLE_NAME, USER_CODE);
        uriMatcher.addURI(AUTHORITY, DbHelper.USER_TABLE_NAME + DbHelper.SPECIAL_CHARACTER,
                USER_CODE_SINGLE);

        uriMatcher.addURI(AUTHORITY, DbHelper.ADDRESS_TABLE_NAME, ADDRESS_CODE);
        uriMatcher.addURI(AUTHORITY, DbHelper.ADDRESS_TABLE_NAME + DbHelper.SPECIAL_CHARACTER,
                ADDRESS_CODE_SINGLE);
        // 若URI资源路径 = content://com.pkqup.android.note/user ，则返回注册码USER_CODE,
        // 即 mMatcher.match(User.USER_CONTENT_URI)的返回值
        // 若URI资源路径 = content://com.pkqup.android.note/address ，则返回注册码ADDRESS_CODE，
        // 即 mMatcher.match(Address.ADDRESS_CONTENT_URI)的返回值

        userHashMap = new HashMap<>();
        userHashMap.put(User.INDEX, User.INDEX);
        userHashMap.put(User.NAME, User.NAME);
        userHashMap.put(User.USER_ID, User.USER_ID);

        addressHashMap = new HashMap<>();
        addressHashMap.put(Address.INDEX, Address.INDEX);
        addressHashMap.put(Address.NAME, Address.NAME);
        addressHashMap.put(Address.PHONE, Address.PHONE);
    }


    // onCreate()方法在ActivityThread#main()运行时间接调用，即ContentProvider是在APP启动的时候就初始化了。运行在主线程，不能做耗时的操作。
    @Override
    public boolean onCreate() {
        resolver = getContext().getContentResolver();
        dbHelper = new DbHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return true;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }


    // 注：以下增删改成四个方法的说明：
    // 1、下面4个方法由外部进程回调，并运行在ContentProvider进程的Binder线程池中（不是主线程）
    // 2、存在多线程并发访问，需要实现线程同步
    // 3、若ContentProvider的数据存储方式是使用SQLite &
    // 一个，则不需要，因为SQLite内部实现好了线程同步，若是多个SQLite则需要，因为SQL对象之间无法进行线程同步
    // 4、若ContentProvider的数据存储方式是内存，则需要自己实现线程同步


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri;
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                long user_id = db.insert(DbHelper.USER_TABLE_NAME, "", values);
                if (user_id < 0) {
                    throw new SQLiteException("Unable to insert " + values + " for " + uri);
                }
                newUri = ContentUris.withAppendedId(uri, user_id);
                resolver.notifyChange(newUri, null);
                break;
            case ADDRESS_CODE:
                long property_id = db.insert(DbHelper.ADDRESS_TABLE_NAME, "", values);
                if (property_id < 0) {
                    throw new SQLiteException("Unable to insert " + values + " for " + uri);
                }
                newUri = ContentUris.withAppendedId(uri, property_id);
                resolver.notifyChange(newUri, null);
                break;
            default:
                throw new IllegalArgumentException("Error Uri: " + uri);
        }
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                count = db.delete(DbHelper.USER_TABLE_NAME, selection, selectionArgs);
                break;
            case USER_CODE_SINGLE:
                String booking_id = uri.getPathSegments().get(1);
                count = db.delete(DbHelper.USER_TABLE_NAME,
                        User.INDEX + "=" + booking_id
                                + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;

            case ADDRESS_CODE:
                count = db.delete(DbHelper.ADDRESS_TABLE_NAME, selection, selectionArgs);
                break;
            case ADDRESS_CODE_SINGLE:
                String property_id = uri.getPathSegments().get(1);
                count = db.delete(DbHelper.ADDRESS_TABLE_NAME,
                        User.INDEX + "=" + property_id
                                + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unnown URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                count = db.update(DbHelper.USER_TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER_CODE_SINGLE:
                String booking_id = uri.getPathSegments().get(1);
                count = db.update(DbHelper.USER_TABLE_NAME, values,
                        User.INDEX + "=" + booking_id
                                + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;

            case ADDRESS_CODE:
                count = db.update(DbHelper.ADDRESS_TABLE_NAME, values, selection, selectionArgs);
                break;
            case ADDRESS_CODE_SINGLE:
                String property_id = uri.getPathSegments().get(1);
                count = db.update(DbHelper.ADDRESS_TABLE_NAME, values,
                        Address.INDEX + "=" + property_id
                                + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unnown URI" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        Cursor cursor = null;
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String orderBy;
        switch (uriMatcher.match(uri)) {
            case USER_CODE:
                qb.setTables(DbHelper.USER_TABLE_NAME);
                // 用户定义列名->数据库列名的映射
                qb.setProjectionMap(userHashMap);
                if (TextUtils.isEmpty(sortOrder)) {
                    orderBy = User.DEFAULT_SORT_ORDER;
                } else {
                    orderBy = sortOrder;
                }
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
                // 用来为Cursor对象注册一个观察数据变化的URI
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            case ADDRESS_CODE:
                qb.setTables(DbHelper.ADDRESS_TABLE_NAME);
                // 用户定义列名->数据库列名的映射
                qb.setProjectionMap(addressHashMap);
                if (TextUtils.isEmpty(sortOrder)) {
                    orderBy = Address.DEFAULT_SORT_ORDER;
                } else {
                    orderBy = sortOrder;
                }
                cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
                // 用来为Cursor对象注册一个观察数据变化的URI
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                break;
        }
        return cursor;
    }
}
