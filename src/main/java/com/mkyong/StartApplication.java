package com.mkyong;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mkyong.exception.CustomException;
import com.mkyong.okhttp.OkHttpHelperGeneric;
import com.mkyong.okhttp.OkhttpHelper;
import com.mkyong.okhttp.UserService;
import com.mkyong.okhttp.retrofit.CustomCallBack;
import com.mkyong.okhttp.retrofit.RetrofitHelper;
import com.mkyong.okhttp.retrofit.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.util.HttpConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

@SpringBootApplication
public class StartApplication {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
       ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);

    }

}
