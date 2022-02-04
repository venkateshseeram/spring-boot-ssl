package com.mkyong.okhttp;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


@Component
public class OkHttpHelperGeneric<T> {


    Gson gson = new Gson();

    private Class<T> realType;

    public OkHttpHelperGeneric() throws ClassNotFoundException {

    }


    public T getresult(String json) {


        return gson.fromJson(json, realType);
    }


}
