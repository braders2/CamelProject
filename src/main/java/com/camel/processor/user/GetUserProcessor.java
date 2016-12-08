package com.camel.processor.user;

import com.camel.dao.UserRepository;
import com.camel.dao.impl.UserRepositoryImpl;
import com.camel.dto.UserDTO;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.GenericTransformer;
import com.camel.transform.impl.UserTransformerImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.restlet.Response;

import java.util.Optional;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class GetUserProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String userId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);
        Gson gson = new Gson();
        UserRepository userRepository = new UserRepositoryImpl();

        Preconditions.checkArgument(Precondition.isInteger(userId), "Invalid user ID passed to argument: " + userId);

        Optional<UserRecord> userRecord = userRepository.get(Long.parseLong(userId));
        if (userRecord.isPresent()) {
            UserRecord userData = userRecord.get();
            GenericTransformer<UserDTO, UserRecord> userTransformer = new UserTransformerImpl();
            UserDTO userDTO = userTransformer.convertToDto(userData);

            exchange.getIn().setBody(gson.toJson(userDTO));
        } else {
            Response response = exchange.getIn().getHeader(RESTLET_RESPONSE, Response.class);
            response.setStatus(SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
