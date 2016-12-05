package com.camel.procesor.project;

import com.camel.dto.ProjectDto;
import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateProjectProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String projectJsonString = exchange.getIn().getBody(String.class);
        ProjectDto projectDto = gson.fromJson(projectJsonString, ProjectDto.class);
        projectDto.setIdProject(exchange.getIn().getHeader("id", Integer.class));
        ProjectDao.updateProject(projectDto);
    }
}
