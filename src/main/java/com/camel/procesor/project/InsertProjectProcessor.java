package com.camel.procesor.project;

import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class InsertProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String userJsonString = exchange.getIn().getBody(String.class);
        JsonElement userJsonElement = gson.fromJson(userJsonString, JsonElement.class);
        JsonObject userJsonObject = userJsonElement.getAsJsonObject();
        ProjectDao.insertProject(userJsonObject);
    }
}
