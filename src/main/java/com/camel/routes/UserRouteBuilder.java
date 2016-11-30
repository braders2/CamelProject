package com.camel.routes;

import com.camel.process.*;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

public class UserRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "user";


    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetUserFromDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetUsersFromDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.POST))
                .process(new InsertUserToDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.PUT))
                .process(new UpdateUserDatabaseProcessor())
                .transform()
                .body();
    }
}
