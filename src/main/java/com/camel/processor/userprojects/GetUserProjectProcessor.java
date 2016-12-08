package com.camel.processor.userprojects;

import com.camel.dao.UserProjectsRepository;
import com.camel.dao.impl.UserProjectsRepositoryImpl;
import com.camel.dto.ProjectDTO;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.transform.impl.ProjectTransformerImpl;
import com.camel.utils.Const;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.Response;

import java.util.ArrayList;
import java.util.Collection;

import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class GetUserProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Gson gson = new Gson();

        String userId = exchange.getIn().getHeader(Const.HEADER_ELEMENT_ID, String.class);
        Preconditions.checkArgument(Precondition.isInteger(userId), "Invalid user ID passed to argument: " + userId);

        UserProjectsRepository userProjectsRepository = new UserProjectsRepositoryImpl();
        Collection<ProjectRecord> projectRecords = userProjectsRepository.getAllUserProjects(Long.parseLong(userId));

        if (!projectRecords.isEmpty()) {
            ProjectTransformerImpl projectTransformer = new ProjectTransformerImpl();
            Collection<ProjectDTO> projectsDTO = new ArrayList<>();

            for (ProjectRecord projectRecord : projectRecords) {
                ProjectDTO projectDTO = projectTransformer.convertToDto(projectRecord);
                projectsDTO.add(projectDTO);
            }

            exchange.getOut().setBody(gson.toJson(projectsDTO));
        } else {
            Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
            response.setStatus(SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
