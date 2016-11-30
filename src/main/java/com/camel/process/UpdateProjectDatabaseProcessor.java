package com.camel.process;

import com.camel.pojos.UserPojo;
import com.camel.utils.UtilsDatabaseMethods;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateUserDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String userJsonString = exchange.getIn().getBody(String.class);
        UserPojo userPojo = gson.fromJson(userJsonString, UserPojo.class);
        userPojo.setIdUser(exchange.getIn().getHeader("id", Integer.class));
        String json = UtilsDatabaseMethods.updateUser(userPojo);

        exchange.getOut().setBody(json);
    }
}
