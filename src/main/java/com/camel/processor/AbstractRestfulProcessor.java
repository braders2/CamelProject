package com.camel.processor;


import com.google.gson.Gson;
import org.apache.camel.Processor;

public abstract class AbstractRestfulProcessor implements Processor {
    private Gson gson;

    public AbstractRestfulProcessor() {
        gson = new Gson();
    }

    protected String convertToJson(Object object) {
        return gson.toJson(object);
    }
}
