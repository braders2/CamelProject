package com.camel.process;

import com.camel.utils.UtilsDatabaseMethods;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUsersFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String jsonListUsers = UtilsDatabaseMethods.getUsers();
        exchange.getOut().setHeader("Content-type", "application/json");
        exchange.getOut().setBody(jsonListUsers);
    }
}
