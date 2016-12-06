package com.camel.procesor.user;

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

import java.util.ArrayList;
import java.util.Collection;

public class GetUsersProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        UserRepository userRepository = new UserRepositoryImpl();

        Collection<UserRecord> userRecords = userRepository.getAll();
        if (!userRecords.isEmpty()) {
            UserTransformerImpl userTransformer = new UserTransformerImpl();
            Collection<UserDTO> usersDTO = new ArrayList<>();

            for (UserRecord userRecord : userRecords) {
                UserDTO userDTO = userTransformer.convertToDto(userRecord);
                usersDTO.add(userDTO);
            }

            exchange.getIn().setBody(gson.toJson(usersDTO));
        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(Status.SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
