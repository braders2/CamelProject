package com.camel.routes;

import com.camel.process.GetUserFromDatabaseProcessor;
import com.camel.process.GetUserProjectFromDatabaseProcessor;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class UserProjectRouteBuilder extends RouteBuilder {
    private final static String RESOURCE_USER = "user";
    private final static String RESOURCE_PROJECT = "project";

    public void configure() throws Exception {
        from(String.format("%s%s/{id}/%s%s%s", Const.URL, RESOURCE_USER, RESOURCE_PROJECT, Const.RESTLET_METHODS, Method.GET))
                .process(new GetUserProjectFromDatabaseProcessor())
                .transform()
                .body();
    }
}
