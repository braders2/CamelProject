package com.camel.processor.project;

import com.camel.dao.ProjectRepository;
import com.camel.dao.impl.ProjectRepositoryImpl;
import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.impl.ProjectTransformerImpl;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;


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

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isInserted ? Status.SUCCESS_CREATED : Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        exchange.getOut().setBody(response);
    }
}
