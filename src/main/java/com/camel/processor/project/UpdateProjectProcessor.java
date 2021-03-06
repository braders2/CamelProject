package com.camel.processor.project;

import com.camel.dao.ProjectRepository;
import com.camel.dao.impl.ProjectRepositoryImpl;
import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.impl.ProjectTransformerImpl;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.Response;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static com.camel.utils.Precondition.isInteger;
import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.REDIRECTION_NOT_MODIFIED;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;


public class UpdateProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String projectId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(isInteger(projectId), "Invalid project ID passed to argument: " + projectId);

        String jsonRequestBody = exchange.getIn().getBody(String.class);
        ProjectDTO projectDTO = gson.fromJson(jsonRequestBody, ProjectDTO.class);
        projectDTO.setIdProject(Integer.parseInt(projectId));

        ProjectTransformerImpl projectTransformer = new ProjectTransformerImpl();
        ProjectRecord projectRecord = projectTransformer.convertToEntity(projectDTO);

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        boolean isUpdated = projectRepository.update(projectRecord);

        Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
        response.setStatus(isUpdated ? SUCCESS_NO_CONTENT : REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
