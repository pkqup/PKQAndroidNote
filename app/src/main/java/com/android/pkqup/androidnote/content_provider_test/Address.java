package com.android.pkqup.androidnote.content_provider_test;

import android.net.Uri;

/**
 * Created by LiuCun on 2017/11/15.<br>
 * Describe
 */

public class Address {

    public static final String INDEX = "_index";// 主键
    public static final String NAME = "name";
    public static final String PHONE = "phone";

    // 定义 address 表的 uri
    public static final Uri ADDRESS_CONTENT_URI = Uri
            .parse("content://" + MyContentProvider.AUTHORITY + "/" + DbHelper.ADDRESS_TABLE_NAME);


    // 定义 address 表的 uri
    public static final Uri ADDRESS_CONTENT_URI_SINGLE =
            Uri.parse("content://" + MyContentProvider.AUTHORITY + "/" + DbHelper.ADDRESS_TABLE_NAME
                    + DbHelper.SPECIAL_CHARACTER);

    // 排序方式，定义为主键排序
    public static final String DEFAULT_SORT_ORDER = INDEX;

}
