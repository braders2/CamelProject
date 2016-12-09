package com.camel.routes;

import com.camel.processor.FailureResponseProcessor;
import com.camel.processor.customer.GetCustomersProcessor;
import org.apache.camel.builder.RouteBuilder;

import static com.camel.utils.Const.RESTLET_METHODS_GET;
import static com.camel.utils.Const.URL;

public class CustomerRouteBuilder extends RouteBuilder {
    private static final String RESOURCE = "/customer";
    private static final String URL_RESOURCE = URL + RESOURCE;

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(URL_RESOURCE + RESTLET_METHODS_GET)
                .process(new GetCustomersProcessor())
                .transform()
                .body();
    }
}
