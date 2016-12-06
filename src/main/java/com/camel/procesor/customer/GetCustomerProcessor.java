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

import java.util.Optional;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;

public class GetCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String customerId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);
        Gson gson = new Gson();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        Preconditions.checkArgument(Precondition.isInteger(customerId),
                "Invalid user ID passed to argument: " + customerId);

        Optional<CustomerRecord> customerRecord = customerRepository.get(Long.parseLong(customerId));

        if (customerRecord.isPresent()) {
            CustomerRecord customerData = customerRecord.get();
            CustomerTransformerImpl customerTransformer = new CustomerTransformerImpl();
            CustomerDTO customerDTO = customerTransformer.convertToDto(customerData);

            exchange.getIn().setBody(gson.toJson(customerDTO));

        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(Status.SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
