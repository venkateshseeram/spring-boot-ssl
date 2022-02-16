package com.mkyong.okhttp;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

public class CustomCallBack<T> implements Callback {


    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private final Class<T> type;

    public CustomCallBack(Class<T> type) {

        this.type = type;


    }

    CompletableFuture<T> future = new CompletableFuture<>();

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

        e.printStackTrace();
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

        if(type.getName().equals("java.lang.String")){

            future.complete((T) response.body().string());

        }

        else {
            T res = gson.fromJson(response.body().string(), type);

            future.complete(res);
        }


    }


    }
