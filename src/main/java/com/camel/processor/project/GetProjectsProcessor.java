package com.camel.processor.project;

import com.camel.dao.ProjectRepository;
import com.camel.dao.impl.ProjectRepositoryImpl;
import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.impl.ProjectTransformerImpl;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.Response;

import java.util.ArrayList;
import java.util.Collection;

import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;


public class GetProjectsProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        Collection<ProjectRecord> projectRecords = projectRepository.getAll();

        if (!projectRecords.isEmpty()) {
            ProjectTransformerImpl projectTransformer = new ProjectTransformerImpl();
            Collection<ProjectDTO> projectsDTO = new ArrayList<>();

            for (ProjectRecord projectRecord : projectRecords) {
                ProjectDTO projectDTO = projectTransformer.convertToDto(projectRecord);
                projectsDTO.add(projectDTO);
            }

            exchange.getIn().setBody(gson.toJson(projectsDTO));
        } else {
            Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
            response.setStatus(SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
