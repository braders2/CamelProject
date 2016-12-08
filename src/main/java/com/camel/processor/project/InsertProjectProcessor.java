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

import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY;
import static org.restlet.data.Status.SUCCESS_CREATED;


public class InsertProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        ProjectTransformerImpl projectTransformer = new ProjectTransformerImpl();

        String jsonRequestBody = exchange.getIn().getBody(String.class);
        ProjectDTO projectDTO = gson.fromJson(jsonRequestBody, ProjectDTO.class);
        ProjectRecord projectRecord = projectTransformer.convertToEntity(projectDTO);

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        boolean isInserted = projectRepository.insert(projectRecord);

        Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
        response.setStatus(isInserted ? SUCCESS_CREATED : CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        exchange.getOut().setBody(response);
    }
}
