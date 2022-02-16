package com.mkyong.okhttp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

@Component
public class OkhttpHelper{

    private final OkHttpClient okHttpClient;

    Gson gson = new Gson();




    @Autowired
    public OkhttpHelper(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }


    public <T> CompletableFuture<T> get(String baseurl, HashMap<String, String> headers, Class<T> responseClass) throws ClassNotFoundException {


       CustomCallBack<T> customCallBack=new CustomCallBack<>(responseClass);

        Request request=new Request.Builder()
                .url(baseurl)
                .build();

         okHttpClient.newCall(request).enqueue(customCallBack);

         return customCallBack.future;
    }
}
