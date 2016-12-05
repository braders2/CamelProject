package com.camel.routes;

import com.camel.procesor.*;
import com.camel.procesor.user.*;
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
                .process(new GetUserProcesor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetUsersProcesor())
                .tracing()
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.POST))
                .process(new InsertUserProcesor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.PUT))
                .process(new UpdateUserProcesor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.DELETE))
                .process(new DeleteUserProcesor())
                .transform()
                .body();
    }
}
