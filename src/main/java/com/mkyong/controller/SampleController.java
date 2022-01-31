package com.mkyong.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    Gson gson = new Gson();

    @GetMapping("/hello")
    public ResponseEntity<JsonObject> index() throws InterruptedException {

        String json = "{ \"name\": \"Baeldung\", \"java\": true }";


        gson.fromJson(json, JsonObject.class);

        //  Thread.sleep(10000);

            return new ResponseEntity<>(gson.fromJson(json, JsonObject.class), HttpStatus.OK);

    }
}
