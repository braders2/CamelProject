package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.utils.ProjectDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class DeleteProjectFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        ProjectDto.deleteProject(exchange.getIn().getHeader("id").toString());
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Accept", "application/json");
        headersMap.put("Status", "204");
        successResponseJsonMessage.setMessage("Success delete project");
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
