package com.camel.procesor.user;

import com.camel.utils.UserDao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class InsertUserProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String userJsonString = exchange.getIn().getBody(String.class);
        JsonElement userJsonElement = gson.fromJson(userJsonString, JsonElement.class);
        JsonObject userJsonObject = userJsonElement.getAsJsonObject();
        UserDao.insertUser(userJsonObject);
    }
}
