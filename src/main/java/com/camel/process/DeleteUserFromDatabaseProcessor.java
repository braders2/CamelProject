package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.utils.UserDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class DeleteUserFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        UserDto.deleteUser(exchange.getIn()
                .getHeader("id").toString());
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Accept", "application/json");
        headersMap.put("Status", "204");
        successResponseJsonMessage.setMessage("Success delete user");
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
