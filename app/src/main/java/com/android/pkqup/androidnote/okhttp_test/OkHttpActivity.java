package com.android.pkqup.androidnote.okhttp_test;

import android.os.Bundle;
import androidx.annotation.NonNull;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.socks.library.KLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by LiuCun on 2017/11/24.<br>
 * Describe
 */

public class OkHttpActivity extends BaseActivity {

    private OkHttpClient okHttpClient;

    private Interceptor logInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            String content = response.body().string();
            KLog.e("----------Request Start----------------");
            KLog.e("| " + request.toString());
            KLog.e("| Response:" + content);
            KLog.e("----------Request End:" + duration + "毫秒----------");
            return response;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initOkHttpClient();
        requestData();

        executeRequest();
        enqueueRequest();
        postFormBodyRequest();

    }


    private void initOkHttpClient() {
        // 使用默认全局配置
        // okHttpClient = new OkHttpClient();


        // 自定义全局配置
        okHttpClient =
                new OkHttpClient.Builder()
                        .connectTimeout(10000, TimeUnit.MILLISECONDS)
                        .readTimeout(10000, TimeUnit.MILLISECONDS)
                        .writeTimeout(10000, TimeUnit.MILLISECONDS)
                        .addInterceptor(logInterceptor)//自定义log拦截器
                        .build();


        // 单独配置某一个请求
        OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder()
                .connectTimeout(20000, TimeUnit.MILLISECONDS).build();
    }

    private void requestData() {
        RequestBody formBody = new FormBody.Builder().add("search", "value").build();

        new Request.Builder().post(formBody)// 请求方法 和 请求体
                .url("https://api.github.com/repos/square/okhttp/issues")// 请求地址
                // 用header(name, value)来为唯一出现的name设置value。如果它本身存在值，在添加新的value之前，他们会被移除。
                // 使用addHeader(name, value)来添加头部不需要移除当前存在的headers。
                .header("User-Agent", "OkHttp Headers.java")// 请求头
                .addHeader("Accept", "application/json; q=0.5")// 请求头
                .addHeader("Accept", "application/vnd.github.v3+json")// 请求头
                .build();
    }

    // 同步请求
    private void executeRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url("http://publicobject.com/helloworld.txt").build();
                    Response response = okHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Headers responseHeaders = response.headers();
                        for (int i = 0; i < responseHeaders.size(); i++) {
                            // Log.e("---executeRequest---",responseHeaders.name(i) + ": " +
                            // responseHeaders.value(i));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 异步请求
    private void enqueueRequest() {
        Request request =
                new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        // Log.e("---enqueueRequest---",responseHeaders.name(i) + ": " +
                        // responseHeaders.value(i));
                    }
                    // System.out.println(response.body().string());
                }
            }
        });
    }

    // 带参数的post请求
    private void postFormBodyRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RequestBody formBody =
                            new FormBody.Builder().add("search", "Jurassic Park").build();
                    Request request = new Request.Builder()
                            .url("https://en.wikipedia.org/w/index.php").post(formBody).build();

                    Response response = okHttpClient.newCall(request).execute();
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    // System.out.println(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
