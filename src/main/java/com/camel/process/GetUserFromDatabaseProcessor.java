package com.camel.process;

import com.camel.utils.UtilsDatabaseMethods;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
       String jsonString = UtilsDatabaseMethods.getUser(exchange.getIn().getHeader("firstname").toString());
       exchange.getOut().setBody(jsonString);
    }
}
