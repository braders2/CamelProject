package com.camel.routes;

import com.camel.procesor.*;
import com.camel.procesor.project.*;
import com.camel.utils.Const;
import org.apache.camel.builder.RouteBuilder;
import org.restlet.data.Method;

import static com.camel.utils.Const.*;

public class ProjectRouteBuilder extends RouteBuilder {
    private static final String RESOURCE = "/project";

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .process(new FailureResponseProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_GET)
                .process(new GetProjectProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESTLET_METHODS_GET)
                .process(new GetProjectsProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESTLET_METHODS_POST)
                .process(new InsertProjectProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_PUT)
                .process(new UpdateProjectProcessor())
                .transform()
                .body();

        from(URL + RESOURCE + RESOURCE_ID + RESTLET_METHODS_DELETE)
                .process(new DeleteProjectProcessor())
                .transform()
                .body();
    }
}
