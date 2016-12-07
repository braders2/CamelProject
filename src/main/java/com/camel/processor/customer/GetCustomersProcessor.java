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

import java.util.ArrayList;
import java.util.Collection;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;

public class GetCustomersProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        String customerId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);
        Gson gson = new Gson();

        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        Collection<CustomerRecord> customerRecords = customerRepository.getAll();

        if (!customerRecords.isEmpty()) {
            CustomerTransformerImpl customerTransformer = new CustomerTransformerImpl();
            Collection<CustomerDTO> customersData = new ArrayList<>();

            for (CustomerRecord customerRecord : customerRecords) {
                CustomerDTO customerDTO = customerTransformer.convertToDto(customerRecord);
                customersData.add(customerDTO);
            }

            exchange.getIn().setBody(gson.toJson(customersData));
        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(Status.SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
