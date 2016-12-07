package com.camel.routes;

import com.camel.processor.FailureResponseProcessor;
import com.camel.processor.user.*;
import org.apache.camel.builder.RouteBuilder;

import static com.camel.utils.Const.*;

public class UserRouteBuilder extends RouteBuilder {
    private static final String RESOURCE = "/user";

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_GET)
                .process(new GetUserProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESTLET_METHODS_GET)
                .process(new GetUsersProcessor())
                .tracing()
                .transform()
                .body();

        from(URL + RESOURCE + RESTLET_METHODS_POST)
                .process(new InsertUserProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_PUT)
                .process(new UpdateUserProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_DELETE)
                .process(new DeleteUserProcessor())
                .transform()
                .body();
    }
}
