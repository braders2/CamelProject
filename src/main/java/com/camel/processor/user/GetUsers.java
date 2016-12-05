package com.camel.processor.user;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.UserDTO;
import com.camel.utils.UserDAO;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUsers implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        List<UserDTO> userDTOs = UserDAO.getUsers();
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");
        exchange.getOut().setHeaders(headersMap);
        successResponseJsonMessage.setMessage("Success get users");
        successResponseJsonMessage.setData(userDTOs);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
