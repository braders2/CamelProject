package com.camel.routes;

import com.camel.process.*;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class ProjectRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "project";

    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetProjectFromDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetProjectsFromDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.POST))
                .process(new InsertProjectToDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.PUT))
                .process(new UpdateProjectDatabaseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.DELETE))
                .process(new DeleteProjectFromDatabaseProcessor())
                .transform()
                .body();
    }
}
