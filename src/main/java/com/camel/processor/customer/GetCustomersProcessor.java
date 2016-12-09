package com.camel.processor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dto.CustomerDTO;
import com.camel.processor.AbstractRestfulProcessor;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.transform.impl.CustomerTransformerImpl;
import org.apache.camel.Exchange;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.Collection;
import java.util.stream.Collectors;

public class GetCustomersProcessor extends AbstractRestfulProcessor {

    // consider to use Inject Constructor
    @Inject
    @Named("customerTransformer")
    CustomerTransformerImpl customerTransformer;

    @Inject
    @Named("customerRepository")
    CustomerRepository customerRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        Collection<CustomerRecord> customerRecords = customerRepository.getAll();

        Collection<CustomerDTO> customersDataResponse = customerRecords.stream()
                .map(customerTransformer::convertToDto)
                .collect(Collectors.toList());
        exchange.getIn().setBody(convertToJson(customersDataResponse));

    }
}
