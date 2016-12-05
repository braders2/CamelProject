package com.camel.processor.user;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.UserDTO;
import com.camel.utils.UserDAO;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateUser implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();

        String userJsonString = exchange.getIn().getBody(String.class);

        UserDTO userDTO = gson.fromJson(userJsonString, UserDTO.class);

        userDTO.setIdUser(exchange.getIn().getHeader("id", Integer.class));
        UserDAO.updateUser(userDTO);

        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");

        successResponseJsonMessage.setMessage("Success update user");
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
