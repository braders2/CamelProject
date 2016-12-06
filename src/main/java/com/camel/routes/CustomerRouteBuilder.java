package com.camel.routes;

import com.camel.procesor.FailureResponseProcessor;
import com.camel.procesor.customer.GetCustomersProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

import static com.camel.utils.Const.*;

public class CustomerRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "/customer";
    private String URL_RESOURCE = URL + RESOURCE;

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
