package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.CustomerDTO;
import com.google.gson.Gson;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Albert on 05.12.2016.
 */
public class GetCustomersFromDatabaseProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        List<CustomerDTO> projectPojos = CustomerDto.getProjects();
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");
        successResponseJsonMessage.setMessage("Success get projects");
        successResponseJsonMessage.setData(projectPojos);
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
