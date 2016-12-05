package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.utils.UserDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class InsertUserToDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        String userJsonString = exchange.getIn().getBody(String.class);
        JsonElement userJsonElement = gson.fromJson(userJsonString, JsonElement.class);
        JsonObject userJsonObject = userJsonElement.getAsJsonObject();
        UserDto.insertUser(userJsonObject);
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "201");
        exchange.getOut().setHeaders(headersMap);
        successResponseJsonMessage.setMessage("Success inserted user");
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
