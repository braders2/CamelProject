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

public class InsertCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        CustomerRecord customerRecord = customerRepository.insert(exchange.getIn().getBody());

        CustomerRecord customerData = customerRecord.get();

        CustomerDTO customerDTO = CustomerDTO.builder()
                .name(customerData.getName())
                .dateCreated(customerData.getDateCreated())
                .contactPerson(customerData.getContactPerson())
                .contactEmail(customerData.getContactEmail())
                .idCustomerStatus(customerData.getIdStatus())
                .build();

        exchange.getIn().setBody(gson.toJson(customerDTO));
    }
}
