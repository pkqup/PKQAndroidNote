package com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test;

import androidx.annotation.NonNull;
import com.android.pkqup.androidnote.abase.MyApplication;
import com.socks.library.KLog;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiuCun on 2017/11/28.<br>
 * Describe
 */

public class RetrofitUtils {

    private static String BASE_URL = "http://api.tianapi.com/";
    public static String WEIXIN_API_KEY = "9a69200c6e0880962216a8a917b59bda";


    private volatile static RetrofitUtils mInstance;

    private FirstService firstService;
    private SecondService secondService;
    private WeiXinService weiXinService;


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

            //在调用了response.body().string()方法之后，response中的流会被关闭，我们需要创建出一个新的response给应用层处理。
            return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), content))
                .build();
        }
    };

    private Interceptor mHeaderInterceptor = new Interceptor() {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.108 Safari/537.36 2345Explorer/8.0.0.13547");
            builder.addHeader("Cache-Control", "max-age=0");
            builder.addHeader("Upgrade-Insecure-Requests", "1");
            builder.addHeader("X-Requested-With", "XMLHttpRequest");
            builder.addHeader("Cookie",
                "uuid=\"w:f2e0e469165542f8a3960f67cb354026\"; __tasessionId=4p6q77g6q1479458262778; csrftoken=7de2dd812d513441f85cf8272f015ce5; tt_webid=36385357187");
            return chain.proceed(builder.build());
        }
    };


    private RetrofitUtils() {
        // OkHttp缓存设置
        File httpCacheDirectory =
            new File(MyApplication.getContext().getCacheDir(), "PkqHttpCache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        OkHttpClient mClient = new OkHttpClient.Builder()
            .addInterceptor(mHeaderInterceptor)// 添加头部信息拦截器
            .addInterceptor(logInterceptor)// 添加log拦截器
            .cache(cache)// 设置网络请求缓存配置
            .connectTimeout(10, TimeUnit.SECONDS)// 设置连接超时时间
            .readTimeout(10, TimeUnit.SECONDS)// 设置读取超时时间
            .build();

        Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)// 配置服务器地址
            .addConverterFactory(GsonConverterFactory.create())// 配置Gson数据转换
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 配置RxJava适配器
            .client(mClient)// 关联OkHttp
            .build();

        firstService = mRetrofit.create(FirstService.class);
        secondService = mRetrofit.create(SecondService.class);
        weiXinService = mRetrofit.create(WeiXinService.class);
    }

    // 获取网络请求的单例（双重校验锁的单例模式）
    public static RetrofitUtils getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtils.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtils();
                }
            }
        }
        return mInstance;
    }

    public FirstService getFirstService() {
        return firstService;
    }

    public void setFirstService(FirstService firstService) {
        this.firstService = firstService;
    }

    public SecondService getSecondService() {
        return secondService;
    }

    public void setSecondService(SecondService secondService) {
        this.secondService = secondService;
    }

    public WeiXinService getWeiXinService() {
        return weiXinService;
    }

    public void setWeiXinService(WeiXinService weiXinService) {
        this.weiXinService = weiXinService;
    }
}
