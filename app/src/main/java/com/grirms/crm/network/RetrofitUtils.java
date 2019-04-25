package com.grirms.crm.network;

import com.google.gson.Gson;
import com.grirms.crm.constant.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019\4\25 0025.
 */

public class RetrofitUtils {
    private static Retrofit retrofit = null;
    private static RetrofitUtils retrofit1 = null;
    public static IParamData getInstance() {
        if (retrofit1 == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit1 == null) {
                    retrofit1 = new RetrofitUtils();
                }
            }
        }
        return retrofit1.createrApi();
    }
    //create RetrofitAPI
    private IParamData createrApi() {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    //打印服务器请求头
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    //okhttp client
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(interceptor)
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(5, TimeUnit.SECONDS)
                            .writeTimeout(5, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();
                    //Retrofit 实例化并返回
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .build();
                }
            }
        }
        return retrofit.create(IParamData.class);
    }
}
