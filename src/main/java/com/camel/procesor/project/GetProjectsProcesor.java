package com.camel.procesor.project;

import com.camel.dto.ProjectDto;
import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetProjectsProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        List<ProjectDto> projectDtos = ProjectDao.getProjects();
    }
}
