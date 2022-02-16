package com.mkyong.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mkyong.annotation.TestAnnotation;
import com.mkyong.okhttp.UserService;
import com.mkyong.okhttp.retrofit.CustomCallBack;
import com.mkyong.okhttp.retrofit.RetrofitHelper;
import com.mkyong.okhttp.retrofit.User;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.layout.LoggerFields;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.message.StringMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.CompletionStage;

@RestController
public class SampleController {

    Gson gson = new Gson();

    @Autowired
    RetrofitHelper retrofitHelper;

    Logger logger= LogManager.getLogger(SampleController.class);

    @GetMapping("/hello")
    @TestAnnotation("hello guru")
    public ResponseEntity<JsonObject> index(@RequestParam String name) throws InterruptedException {

        String json = "{ \"name\": \"fast\", \"java\": true }";

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        jsonObject.addProperty("requester",name);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);

    }

    @GetMapping("/hello2")
    public ResponseEntity<JsonObject> index2(@RequestParam String name) throws InterruptedException {

        String json = "{ \"name\": \"late\", \"java\": true }";

        Thread.sleep(2000);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        jsonObject.addProperty("requester",name);

        //  Thread.sleep(10000);

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);

    }

    @GetMapping("/user/{id}")
    public CompletionStage<User> index3(@PathVariable String id) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());


        UserService userService=retrofitHelper.getRetrofit("https://gorest.co.in/").create(UserService.class);

        CustomCallBack<User> customCallBack=new CustomCallBack<>();

        userService.getUser(id).enqueue(customCallBack);

        User user=customCallBack.getFuture().toCompletableFuture().join();



        return customCallBack.getFuture();


    }

}

@ToString
class LogObject{
    public LogObject() {
        this.attributes = new HashMap<>();
    }

     HashMap<String, Object> attributes;


}
