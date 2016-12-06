package com.camel.procesor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dao.impl.CustomerRepositoryImpl;
import com.camel.dto.CustomerDTO;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.transform.impl.CustomerTransformerImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;
import static org.restlet.data.Status.SUCCESS_NO_CONTENT;

public class UpdateCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String customerId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(customerId), "Invalid user ID passed to argument: " + customerId);

        String jsonRequestBody = exchange.getIn().getBody(String.class);

        CustomerDTO customerDTO = gson.fromJson(jsonRequestBody, CustomerDTO.class);
        customerDTO.setIdCustomer(Integer.parseInt(customerId));

        CustomerTransformerImpl customerTransformer = new CustomerTransformerImpl();
        CustomerRecord customerRecord = customerTransformer.convertToEntity(customerDTO);
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        boolean isUpdated = customerRepository.update(customerRecord);

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isUpdated ? SUCCESS_NO_CONTENT : Status.REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
