package com.mkyong;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Threadlocalex {


    void sample() throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException {

        ThreadLocal<IfiContext> ifiContextThreadLocal=new ThreadLocal<>();
        ifiContextThreadLocal.set(new IfiContext(140793L));

        Field field = Thread.class.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object thread = field.get(Thread.currentThread());
        Field table = Class.forName("java.lang.ThreadLocal$ThreadLocalMap").getDeclaredField("table");
        table.setAccessible(true);
        Object tbl = table.get(thread);
        int length = Array.getLength(tbl);
        for(int i = 0; i < length; i++) {
            Object entry = Array.get(tbl, i);
            Object value = null;
            String valueClass = null;
            if(entry != null) {
                Field valueField = Class.forName("java.lang.ThreadLocal$ThreadLocalMap$Entry").getDeclaredField("value");
                valueField.setAccessible(true);
                value = valueField.get(entry);
                if(value != null) {
                    valueClass = value.getClass().getName();

                    if(value instanceof IfiContext){

                        Long ifi= ((IfiContext) value).getIfi();

                        System.out.println(ifi);
                    }
                }
                //  Logger.getRootLogger().info("[" + i + "] type[" + valueClass + "] " + value);


                System.out.println("[" + i + "] type[" + valueClass + "] " + value);
            }
        }
    }
}
