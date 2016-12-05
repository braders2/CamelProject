package com.camel.routes;

import com.camel.process.FailureResponseProcessor;
import com.camel.process.GetCustomersFromDatabaseProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

/**
 * Created by Albert on 05.12.2016.
 */
public class CustomerRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "customer";

    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetCustomersFromDatabaseProcessor())
                .transform()
                .body();
    }
}
