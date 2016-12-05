package com.camel.processor;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.ProjectPojo;
import com.camel.utils.ProjectDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetProjectFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        ProjectPojo projectPojo = ProjectDto.getProject(exchange.getIn()
                .getHeader("id").toString());
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");
        successResponseJsonMessage.setMessage("Success get projects");
        successResponseJsonMessage.setData(projectPojo);
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
