package com.camel.process;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.ProjectPojo;
import com.camel.utils.ProjectDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateProjectDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String projectJsonString = exchange.getIn().getBody(String.class);
        ProjectPojo projectPojo = gson.fromJson(projectJsonString, ProjectPojo.class);
        projectPojo.setIdProject(exchange.getIn().getHeader("id", Integer.class));
        ProjectDto.updateProject(projectPojo);
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");
        exchange.getOut().setHeaders(headersMap);
        successResponseJsonMessage.setMessage("Success update project");
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
