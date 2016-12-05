package com.camel.procesor.project;

import com.camel.dto.ProjectDto;
import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetProjectProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        ProjectDto projectDto = ProjectDao.getProject(exchange.getIn()
                .getHeader("id").toString());
    }
}
