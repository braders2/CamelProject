package com.camel.process;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class FailureResponseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        StringBuilder errorStringBuilder = new StringBuilder();
        errorStringBuilder.append("Error: ");
        errorStringBuilder.append(e.getCause());
        Gson gson = new Gson();
        exchange.getOut().setBody(gson.toJson(errorStringBuilder));
    }
}
