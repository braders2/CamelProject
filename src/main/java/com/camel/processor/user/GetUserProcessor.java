package com.camel.processor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.pojos.UserDTO;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.Optional;

public class GetUserProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String userId = exchange.getIn().getHeader("id").toString();

        Preconditions.checkArgument(Precondition.isInteger(userId), "Invalid userID passed to processor:" + userId);

        Gson gson = new Gson();

        UserRepository userRepository = new UserRepositoryImpl();
        Optional<UserRecord> userRecord = userRepository.get(Long.parseLong(userId));

        if (userRecord.isPresent()) {
            UserRecord userData = userRecord.get();

            UserDTO userDTO = UserDTO.builder()
                .email(userData.getEmail())
                .surname(userData.getSurname())
                .firstname(userData.getFirstname())
                .build();

            exchange.getIn().setBody(gson.toJson(userDTO));

        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(Status.SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }

    }
}
