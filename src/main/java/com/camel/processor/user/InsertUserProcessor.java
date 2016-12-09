package com.camel.processor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.dto.UserDTO;
import com.camel.processor.AbstractRestfullProcessor;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.impl.UserTransformerImpl;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.Response;

import java.util.Optional;

import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY;
import static org.restlet.data.Status.SUCCESS_CREATED;

public class InsertUserProcessor extends AbstractRestfullProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {
        boolean isInserted = false;

        UserTransformerImpl userTransformer = new UserTransformerImpl();

        String jsonRequestBody = exchange.getIn().getBody(String.class);
        Optional<UserDTO> optionalUserDTO = Optional.ofNullable(convertFromJson(jsonRequestBody, UserDTO.class));

        if (optionalUserDTO.isPresent()) {
            UserDTO userDTO = optionalUserDTO.get();
            UserRecord userRecord = userTransformer.convertToEntity(userDTO);

            UserRepository userRepository = new UserRepositoryImpl();
            isInserted = userRepository.insert(userRecord);
        }

        Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
        response.setStatus(isInserted ? SUCCESS_CREATED : CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        exchange.getOut().setBody(response);
    }
}
