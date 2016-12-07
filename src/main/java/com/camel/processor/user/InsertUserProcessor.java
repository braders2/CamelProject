package com.camel.processor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.dto.UserDTO;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.impl.UserTransformerImpl;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

public class InsertUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        UserTransformerImpl userTransformer = new UserTransformerImpl();
        String jsonRequestBody = exchange.getIn().getBody(String.class);
        UserRecord userRecord = userTransformer.convertToEntity(gson.fromJson(jsonRequestBody, UserDTO.class));

        UserRepository userRepository = new UserRepositoryImpl();
        boolean isInserted = userRepository.insert(userRecord);

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isInserted ? Status.SUCCESS_CREATED : Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        exchange.getOut().setBody(response);
    }
}
