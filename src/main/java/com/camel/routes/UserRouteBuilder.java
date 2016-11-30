package com.camel.routes;

import com.camel.process.FailureResponseProcessor;
import com.camel.process.InsertUserToDatabaseProcessor;
import com.camel.process.GetUserFromDatabaseProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;

public class UserRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "user";


    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{firstname}%s", Const.URL, RESOURCE, Const.METHOD_GET))
                .process(new GetUserFromDatabaseProcessor())
                .transform()
                .body();
        from(String.format("%s%s%s", Const.URL, RESOURCE, Const.METHOD_POST))
                .process(new InsertUserToDatabaseProcessor())
                .transform()
                .body();
    }
}
