package com.mkyong.okhttp.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;

public class CustomCallBack<T> implements Callback<T> {


    private CompletableFuture<T> future;

    public CustomCallBack() {
        this.future = new CompletableFuture<>();
    }

    public CompletableFuture<T> getFuture() {
        return future;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {


        if(response.isSuccessful()) {

            future.complete(response.body());

        }

        else{

            future.completeExceptionally(new HttpException(response));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {


        future.completeExceptionally(throwable);
    }
}
