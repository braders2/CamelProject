package com.camel.process;

import com.camel.utils.UtilsDatabaseMethods;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String userJson = UtilsDatabaseMethods.getUser(exchange.getIn()
                .getHeader("id").toString());
        if (userJson.isEmpty()) {
            Message message = exchange.getOut();
            message.setFault(true);
            message.setBody("No data found");
        } else {
            exchange.getOut().setBody(userJson);
        }
    }
}
