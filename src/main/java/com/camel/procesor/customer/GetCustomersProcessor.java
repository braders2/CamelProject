package com.camel.procesor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dao.impl.CustomerRepositoryImpl;
import com.camel.dto.CustomerDTO;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import java.util.Optional;

public class GetCustomersProcessor implements org.apache.camel.Processor {
    public void process(Exchange exchange) throws Exception {
        String customerId = exchange.getIn().getHeader("id").toString();

        Preconditions.checkArgument(Precondition.isInteger(customerId),
                "Invalid customerID passed to processor (CustomerID = " + customerId + "\"");

        Gson gson = new Gson();

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        Optional<CustomerRecord> customerRecord = customerRepository.get(Long.parseLong(customerId));

        if (customerRecord.isPresent()) {
            CustomerRecord customerData = customerRecord.get();

            CustomerDTO customerDTO = CustomerDTO.builder()
                    .name(customerData.getName())
                    .dateCreated(customerData.getDateCreated())
                    .contactPerson(customerData.getContactPerson())
                    .contactEmail(customerData.getContactEmail())
                    .idCustomerStatus(customerData.getIdStatus())
                    .build();

            exchange.getIn().setBody(gson.toJson(customerDTO));

        } else {
            Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
            response.setStatus(Status.SUCCESS_NO_CONTENT);
            exchange.getOut().setBody(response);
        }
    }
}
