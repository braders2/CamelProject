package com.camel.procesor.project;

import com.camel.dto.ProjectDTO;
import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;


public class UpdateProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String projectJsonString = exchange.getIn().getBody(String.class);
        ProjectDTO projectDTO = gson.fromJson(projectJsonString, ProjectDTO.class);
        projectDTO.setIdProject(exchange.getIn().getHeader("id", Integer.class));
        ProjectDao.updateProject(projectDTO);
    }
}
