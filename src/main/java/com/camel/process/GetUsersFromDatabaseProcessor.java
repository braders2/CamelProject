package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.UserPojo;
import com.camel.utils.UserDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUsersFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        List<UserPojo> userPojos = UserDto.getUsers();
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Accept", "application/json");
        headersMap.put("Status", "200");
        exchange.getOut().setHeaders(headersMap);
        successResponseJsonMessage.setMessage("Success get users");
        successResponseJsonMessage.setData(userPojos);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
