package com.android.pkqup.androidnote.content_provider_test;

import android.net.Uri;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class User {

    public static final String INDEX = "_index";// 主键
    public static final String NAME = "name";
    public static final String USER_ID = "userId";


    // 定义 user 表的 uri
    public static final Uri USER_CONTENT_URI =
            Uri.parse("content://" + MyContentProvider.AUTHORITY + "/" + DbHelper.USER_TABLE_NAME);

    // 定义 user 表的 uri
    public static final Uri USER_CONTENT_URI_SINGLE =
            Uri.parse("content://" + MyContentProvider.AUTHORITY + "/" + DbHelper.USER_TABLE_NAME
                    + DbHelper.SPECIAL_CHARACTER);

    // 排序方式，定义为主键排序
    public static final String DEFAULT_SORT_ORDER = INDEX;

    private String name;
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
