package com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test;

import android.os.Bundle;
import android.os.Environment;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.socks.library.KLog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiuCun on 2017/11/24.<br>
 * Describe
 */

public class RetrofitActivity extends BaseActivity {

    private FirstService firstService;
    private WeiXinService weiXinService;

    private static final int PAGE_NUM = 10;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        firstService = RetrofitUtils.getInstance().getFirstService();
        weiXinService = RetrofitUtils.getInstance().getWeiXinService();

        uploadSingleFile();

        uploadMultiFile();

        getNewsList();
    }

    private void getNewsList() {
        weiXinService.getWeixinHorList(RetrofitUtils.WEIXIN_API_KEY, PAGE_NUM, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<WeiXinNewsBean>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(WeiXinNewsBean weiXinNewsBean) {
                    KLog.e(weiXinNewsBean.getCode()+" ---- "+weiXinNewsBean.getMsg());
                }

                @Override
                public void onError(Throwable e) {
                    KLog.e(e.toString());
                }

                @Override
                public void onComplete() {

                }
            });
    }


    // Retrofit单文件上传
    private void uploadSingleFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "icon.png");
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
        MultipartBody.Part photo =
            MultipartBody.Part.createFormData("photos", "icon.png", photoRequestBody);

        Call<User> call = firstService.registerUser(photo, RequestBody.create(null, "abc"),
            RequestBody.create(null, "123"));
    }

    // Retrofit多文件上传
    private void uploadMultiFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "messenger_01.png");
        RequestBody photo = RequestBody.create(MediaType.parse("image/png"), file);
        Map<String, RequestBody> photos = new HashMap<>();
        photos.put("photos\"; filename=\"icon.png", photo);
        photos.put("username", RequestBody.create(null, "abc"));

        Call<User> call = firstService.registerUser(photos, RequestBody.create(null, "123"));
        try {
            Response<User> execute = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}
