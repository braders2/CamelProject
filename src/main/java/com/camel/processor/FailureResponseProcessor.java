package com.camel.processor;

import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class FailureResponseProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        Message message = exchange.getIn();
        message.setBody(exception.getMessage());
        message.setHeader("Status", "400");
    }
}
