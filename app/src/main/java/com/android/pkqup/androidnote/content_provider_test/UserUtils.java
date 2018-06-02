package com.android.pkqup.androidnote.content_provider_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class UserUtils {

    private Context context;

    public UserUtils(Context context) {
        this.context = context;
    }

    public void insertUser(String name, String age) {
        ContentValues values = new ContentValues();
        values.put(User.NAME, name);
        values.put(User.USER_ID, age);
        context.getContentResolver().insert(User.USER_CONTENT_URI, values);
    }

    public void deleteUser() {
        context.getContentResolver().delete(User.USER_CONTENT_URI, null, null);
    }

    public void updateUser(String name, String age) {
        ContentValues values = new ContentValues();
        values.put(User.NAME, name);
        values.put(User.USER_ID, age);
        context.getContentResolver().update(User.USER_CONTENT_URI, values, null, null);
    }

    public User queryUser() {
        User user = new User();
        Cursor mCursor = context.getContentResolver().query(User.USER_CONTENT_URI, null, null, null,
                User.DEFAULT_SORT_ORDER);
        if (null != mCursor) {
            while (mCursor.moveToNext()) {
                String name = mCursor.getString(mCursor.getColumnIndexOrThrow(User.NAME));
                String age = mCursor.getString(mCursor.getColumnIndexOrThrow(User.USER_ID));
                user.setName(name);
                user.setUserId(age);
            }
            mCursor.close();
        }
        return user;
    }


    // 数据库查询方法的参数说明
    public void query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        // projection表示要查询哪些列，比如查询媒体库，可能要关注音乐的艺术家，长度，文件位置等。
        // selection表示查询条件，就是查询里面的where语句，
        // selectionArgs是查询条件的值。
        // sortOrder是排序方式。
    }

    // 根据id查询名称
    public String getUserNameFrom(String userId) {
        String userName = "";
        Cursor mCursor = context.getContentResolver().query(User.USER_CONTENT_URI,
                new String[] {User.NAME, User.USER_ID}, "userId=?", new String[] {userId},
                User.DEFAULT_SORT_ORDER);
        if (null != mCursor) {
            while (mCursor.moveToNext()) {
                userName = mCursor.getString(mCursor.getColumnIndexOrThrow(User.NAME));
            }
            mCursor.close();
        }
        return userName;
    }
}
