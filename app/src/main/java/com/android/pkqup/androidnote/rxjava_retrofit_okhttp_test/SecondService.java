package com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LiuCun on 2017/11/28.<br>
 * Describe
 */

public interface SecondService {

    @GET("/address")
    Call<Address> getAddress();

}
