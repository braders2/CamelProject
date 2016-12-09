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

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.apache.camel.component.restlet.RestletConstants.RESTLET_RESPONSE;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class GetUsersProcessor extends AbstractRestfullProcessor {

    @Override
    public void process(Exchange exchange) throws Exception {
        UserRepository userRepository = new UserRepositoryImpl();

        Collection<UserRecord> userRecords = userRepository.getAll();
        UserTransformerImpl userTransformer = new UserTransformerImpl();
        Collection<UserDTO> usersDataResponse = userRecords.stream().map(userTransformer::convertToDto)
                .collect(Collectors.toList());
        exchange.getIn().setBody(convertToJson(usersDataResponse));

    }
}
