package com.camel.processor.customer;

import com.camel.dao.CustomerRepository;
import com.camel.dao.impl.CustomerRepositoryImpl;
import com.camel.utils.Precondition;
import com.google.common.base.Preconditions;
import org.apache.camel.Exchange;
import org.apache.camel.component.restlet.RestletConstants;
import org.restlet.Response;
import org.restlet.data.Status;

import static com.camel.utils.Const.HEADER_ELEMENT_ID;

public class DeleteCustomerProcessor implements org.apache.camel.Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String customerId = exchange.getIn().getHeader(HEADER_ELEMENT_ID, String.class);

        Preconditions.checkArgument(Precondition.isInteger(customerId),
                "Invalid customerID passed to processor: " + customerId);

        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        boolean isDeleted = customerRepository.delete(Long.parseLong(customerId));

        Response response = exchange.getIn().getHeader(RestletConstants.RESTLET_RESPONSE, Response.class);
        response.setStatus(isDeleted ? Status.SUCCESS_NO_CONTENT : Status.REDIRECTION_NOT_MODIFIED);
        exchange.getOut().setBody(response);
    }
}
