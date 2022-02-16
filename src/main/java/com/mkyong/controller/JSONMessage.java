package com.mkyong.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.message.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONMessage implements Message {

    private static final long serialVersionUID = 538439258494853324L;
    private String messageString;
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public JSONMessage(){
        this(null);
    }

    public JSONMessage(Object msgObj){
        parseMessageAsJson(msgObj);
    }

    public JSONMessage(String msgStr){
        Map<String,String> msgObj = new HashMap<>();
        msgObj.put("message", msgStr);
        parseMessageAsJson(msgObj);
    }

    private void parseMessageAsJson(Object msgObj){
        messageString = GSON.toJson(msgObj);
    }

    @Override
    public String getFormattedMessage() {
        return messageString;
    }

    @Override
    public String getFormat() {
        return messageString;
    }

    @Override
    public Object[] getParameters() {
        return null;
    }

    @Override
    public Throwable getThrowable() {
        return null;
    }

}
