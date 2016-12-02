package com.camel.process;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class FailureResponseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        StringBuilder errorStringBuilder = new StringBuilder();
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        errorStringBuilder.append("Error: ");
        errorStringBuilder.append(exception.getMessage());
        exchange.getOut().setHeader("Content-type", "application/json");
        exchange.getOut().setBody(gson.toJson(errorStringBuilder));
    }
}
