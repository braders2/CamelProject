package com.camel.process;

import com.camel.models.ErrorResponseJsonMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class FailureResponseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        ErrorResponseJsonMessage errorResponseJsonMessage = new ErrorResponseJsonMessage();
        Gson gson = new Gson();
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        errorResponseJsonMessage.setCode("400");
        errorResponseJsonMessage.setMessage(exception.getMessage());
        JsonElement jsonElement = gson.toJsonTree(errorResponseJsonMessage);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("error", jsonElement);
        exchange.getOut().setHeader("Accept", "application/json");
        exchange.getOut().setHeader("Status", "400");
        exchange.getOut().setBody(jsonObject);
    }
}
