package com.mkyong;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mkyong.exception.CustomException;
import com.mkyong.okhttp.OkhttpHelper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class StartApplication {


    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, ClassNotFoundException {
        ConfigurableApplicationContext context = SpringApplication.run(StartApplication.class, args);


       MyHttpHelper http = context.getBean(MyHttpHelper.class);

/*
        CompletionStage<JsonObject> reponse = http.get("https://localhost:8443/hello", Collections.EMPTY_MAP, Collections.emptyMap(), JsonObject.class);

          reponse.thenAccept(jsonObject -> System.out.println(jsonObject.toString())).exceptionally(throwable -> {

              throwable.printStackTrace();

              return null;
       });*/

        OkhttpHelper helper = context.getBean(OkhttpHelper.class);


         helper.get("https://localhost:8443/hello2", new HashMap<String, String>(), JsonObject.class).thenAccept(response->System.out.println(response.toString()));
         helper.get("https://localhost:8443/hello", new HashMap<String, String>(), JsonObject.class).thenAccept(response->System.out.println(response.toString()));




    }

}
