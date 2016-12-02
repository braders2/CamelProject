package com.camel.process;

import com.camel.utils.UserDto;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String userJson = UserDto.getUser(exchange.getIn()
                .getHeader("id").toString());
        if (userJson.isEmpty()) {
            Message message = exchange.getOut();
            message.setFault(true);
            message.setBody("No data found");
        } else {
            exchange.getOut().setHeader("Content-type", "application/json");
            exchange.getOut().setBody(userJson);
        }
    }
}
