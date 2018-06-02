package com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by LiuCun on 2017/11/28.<br>
 * Describe
 */

public interface FirstService {

    @GET("user")
    Call<User> getUser();


    // 1、路径替换的url请求方式
    // 请求的地址是http://www.BaseURL.com/login， url.get("login");
    @GET("{name}")
    Call<User> getUser1(@Path("name") String urlName);// 注意@Path和{}中的参数名要一致

    // 2、GET带参数的请求(@Query 单个参数)
    // 例：www.BaseURL.com/login?username=name&password=123456
    @GET("login")
    Call<User> login(@Query("username") String name, @Query("password") String password);

    //3、GET带参数的请求(@QueryMap 多个参数)
    @GET("login")
    Call<User> queryMap(@QueryMap Map<String, String> maps);


    // 4、POST带body的请求(Retrofit2 会自动把实体转成Gson)
    @POST("user/info")
    Call<User> send(@Body UserInfo userInfo);

    //5、表单的方式传递键值对 @FormUrlEncoded 和 @Field
    @POST("login")
    @FormUrlEncoded
    Call<User> login2(@Field("username") String username, @Field("password") String password);
    //或者
    @POST("login")
    @FormUrlEncoded
    Call<User> login3(@FieldMap Map<String, String> maps);

    //6、单文件上传 @Multipart——表示允许多个part
    @Multipart
    @POST("register")
    Call<User> registerUser(@Part MultipartBody.Part photo, @Part("username") RequestBody username, @Part("password") RequestBody password);


    //7、多文件上传
    @Multipart
    @POST("register")
    Call<User> registerUser(@PartMap Map<String, RequestBody> params, @Part("password") RequestBody password);

}


