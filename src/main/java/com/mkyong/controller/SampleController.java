package com.mkyong.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    Gson gson = new Gson();

    @GetMapping("/hello")
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
}
