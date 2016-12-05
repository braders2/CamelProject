package com.camel.routes;

import com.camel.processor.*;
import com.camel.processor.user.*;
import org.apache.camel.builder.RouteBuilder;

import static com.camel.utils.Const.*;

public class UserRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "/user";
    private String URL_RESOURCE = URL + RESOURCE;

    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(URL_RESOURCE + RESOURCE_ID + RESTLET_METHODS_GET)
                .process(new GetUserProcessor())
                .transform()
                .body();

        from(URL_RESOURCE + RESTLET_METHODS_GET)
                .process(new GetUsers())
                .tracing()
                .transform()
                .body();

        from(URL_RESOURCE + RESTLET_METHODS_POST)
                .process(new InsertUser())
                .transform()
                .body();

        from(URL_RESOURCE + RESOURCE_ID + RESTLET_METHODS_PUT)
                .process(new UpdateUser())
                .transform()
                .body();

        from(URL_RESOURCE + RESOURCE_ID + RESTLET_METHODS_DELETE)
                .process(new DeleteUser())
                .transform()
                .body();
    }
}
