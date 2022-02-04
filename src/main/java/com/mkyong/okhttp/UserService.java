package com.mkyong.okhttp;

import com.mkyong.okhttp.retrofit.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UserService {

    @GET("public/v2/users")
    public Call<List<User>> getUsers(
            @Query("per_page") Integer per_page,
            @Query("page") Integer page);

    @GET("public/v2/users/{id}")
    public Call<User> getUser(@Path("id") String id);
}


