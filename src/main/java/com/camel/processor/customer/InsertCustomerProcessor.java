package com.camel.processor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dao.impl.CustomerRepositoryImpl;
import com.camel.dto.CustomerDTO;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.transform.impl.CustomerTransformerImpl;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

public class InsertCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        CustomerTransformerImpl customerTransformer = new CustomerTransformerImpl();

        String jsonRequestBody = exchange.getIn().getBody(String.class);
        CustomerRecord customerRecord = customerTransformer
                .convertToEntity(gson.fromJson(jsonRequestBody, CustomerDTO.class));

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        boolean isInserted = customerRepository.insert(customerRecord);

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isInserted ? Status.SUCCESS_CREATED : Status.CLIENT_ERROR_UNPROCESSABLE_ENTITY);
        exchange.getOut().setBody(response);
    }
}
