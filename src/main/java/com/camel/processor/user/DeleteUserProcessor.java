package com.camel.processor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;

public class DeleteUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String userId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(userId), "Invalid user ID passed to argument: " + userId);

        UserRepository userRepository = new UserRepositoryImpl();
        boolean isDeleted = userRepository.delete(Long.parseLong(userId));

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isDeleted ? Status.SUCCESS_NO_CONTENT : Status.REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
