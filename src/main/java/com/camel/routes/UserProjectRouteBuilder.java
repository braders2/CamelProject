package com.camel.routes;

import com.camel.processor.FailureResponseProcessor;
import com.camel.processor.GetUserProjectProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

public class UserProjectRouteBuilder extends RouteBuilder {
    private static final String RESOURCE_USER = "user";
    private static final String RESOURCE_PROJECT = "project";

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}/%s%s%s", Const.URL, RESOURCE_USER, RESOURCE_PROJECT, Const.RESTLET_METHODS, Method.GET))
                .process(new GetUserProjectProcessor())
                .transform()
                .body();
    }
}
