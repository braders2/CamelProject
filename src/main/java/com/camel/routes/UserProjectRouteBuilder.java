package com.camel.routes;

import com.camel.processor.FailureResponseProcessor;
import com.camel.processor.userprojects.GetUserProjectProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;

import static com.camel.utils.Const.*;

public class UserProjectRouteBuilder extends RouteBuilder {
    private final static String RESOURCE_USER = "/user";
    private final static String RESOURCE_PROJECT = "/project";

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(URL + RESOURCE_USER + Const.RESOURCE_ID + RESOURCE_PROJECT + RESTLET_METHODS_GET)
                .process(new GetUserProjectProcessor())
                .transform()
                .body();
    }
}
