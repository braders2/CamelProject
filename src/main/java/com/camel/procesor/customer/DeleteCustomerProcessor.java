package com.camel.procesor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dao.impl.CustomerRepositoryImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import org.apache.camel.Exchange;

public class DeleteCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String customerId = exchange.getIn().getHeader("id").toString();

        Preconditions.checkArgument(Precondition.isInteger(customerId),
                "Invalid customerID passed to processor (CustomerID = " + customerId + "\"");

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        customerRepository.delete(Long.parseLong(customerId));
    }
}
