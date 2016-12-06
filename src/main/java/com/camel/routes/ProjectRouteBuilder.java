package com.camel.routes;

import com.camel.procesor.*;
import com.camel.procesor.project.*;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

public class ProjectRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "project";

    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetProjectProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.GET))
                .process(new GetProjectsProcessor())
                .transform()
                .body();

        from(String.format("%s%s%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.POST))
                .process(new InsertProjectProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.PUT))
                .process(new UpdateProjectProcessor())
                .transform()
                .body();

        from(String.format("%s%s/{id}%s%s", Const.URL, RESOURCE, Const.RESTLET_METHODS, Method.DELETE))
                .process(new DeleteProjectProcessor())
                .transform()
                .body();
    }
}
