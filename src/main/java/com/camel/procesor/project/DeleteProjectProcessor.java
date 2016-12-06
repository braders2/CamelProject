package com.camel.procesor.project;

import com.camel.dao.ProjectRepository;
import com.camel.dao.impl.ProjectRepositoryImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static org.restlet.data.Status.REDIRECTION_NOT_MODIFIED;

public class DeleteProjectProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String projectId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(projectId), "Invalid project ID passed to argument: " + projectId);

        ProjectRepository projectRepository = new ProjectRepositoryImpl();
        boolean isDeleted = projectRepository.delete(Long.parseLong(projectId));

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isDeleted ? Status.SUCCESS_NO_CONTENT : REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
