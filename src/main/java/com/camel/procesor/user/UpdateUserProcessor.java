package com.camel.procesor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.dto.UserDTO;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.impl.UserTransformerImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class UpdateUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String userId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(userId), "Invalid user ID passed to argument: " + userId);

        String jsonRequestBody = exchange.getIn().getBody(String.class);
        UserDTO userDTO = gson.fromJson(jsonRequestBody, UserDTO.class);
        userDTO.setIdUser(Integer.parseInt(userId));

        UserTransformerImpl userTransformer = new UserTransformerImpl();
        UserRecord userRecord = userTransformer.convertToEntity(userDTO);

        UserRepository userRepository = new UserRepositoryImpl();
        boolean isUpdated = userRepository.update(userRecord);

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isUpdated ? SUCCESS_NO_CONTENT : Status.REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
