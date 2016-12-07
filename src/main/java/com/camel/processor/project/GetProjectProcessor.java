package com.camel.processor.project;

import com.camel.dao.ProjectRepository;
import com.camel.dao.impl.ProjectRepositoryImpl;
import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.impl.ProjectTransformerImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;

import java.util.Optional;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class GetProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        String projectId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(projectId), "Invalid project ID passed to argument: " + projectId);

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        Optional<ProjectRecord> projectRecord = projectRepository.get(Long.parseLong(projectId));

        if (projectRecord.isPresent()) {
            ProjectRecord projectData = projectRecord.get();

            ProjectTransformerImpl projectTransformer = new ProjectTransformerImpl();
            ProjectDTO projectDTO = projectTransformer.convertToDto(projectData);

            exchange.getIn().setBody(gson.toJson(projectDTO));
        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
