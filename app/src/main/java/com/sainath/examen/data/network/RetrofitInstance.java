package com.sainath.examen.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;

    private static final String BASE_URL ="http://192.168.1.240:8080/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit ==null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5,TimeUnit.MINUTES)
                    .readTimeout(5,TimeUnit.MINUTES)
                    .build();
            Gson gson = new GsonBuilder().serializeNulls().create();
            retrofit  = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }



}
