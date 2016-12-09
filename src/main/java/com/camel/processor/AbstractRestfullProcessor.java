package com.camel.processor;

import com.google.gson.Gson;
import org.apache.camel.Processor;

import java.lang.reflect.Type;

public abstract class AbstractRestfullProcessor implements Processor {
    private Gson gson;

    public AbstractRestfullProcessor() {
        this.gson = new Gson();
    }

    protected String convertToJson(Object objcet) {
        return gson.toJson(objcet);
    }

    protected <T>  T convertFromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }
}
