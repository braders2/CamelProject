package com.camel.processor.project;

import com.camel.models.SuccessResponseJsonMessage;
import com.camel.pojos.ProjectPojo;
import com.camel.utils.ProjectDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetProjectsFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        SuccessResponseJsonMessage successResponseJsonMessage = new SuccessResponseJsonMessage();
        Gson gson = new Gson();
        List<ProjectPojo> projectPojos = ProjectDto.getProjects();
        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Content-type", "application/json");
        headersMap.put("Status", "200");
        successResponseJsonMessage.setMessage("Success get projects");
        successResponseJsonMessage.setData(projectPojos);
        exchange.getOut().setHeaders(headersMap);
        exchange.getOut().setBody(gson.toJson(successResponseJsonMessage));
    }
}
