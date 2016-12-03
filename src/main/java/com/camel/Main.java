package com.camel;

import com.camel.routes.ProjectRouteBuilder;
import com.camel.routes.UserProjectRouteBuilder;
import com.camel.routes.UserRouteBuilder;
import com.camel.utils.UtilsDatabaseJooq;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

    public static void main(String args[]) throws Exception {
        CamelContext camelContext = initializeCamel();
        UtilsDatabaseJooq.initialzeConnection();
        camelContext.start();
        Thread.sleep(10000000);
        camelContext.stop();

    }


    public static CamelContext initializeCamel() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.setHandleFault(true);
        UserRouteBuilder userRouteBuilder = new UserRouteBuilder();
        ProjectRouteBuilder projectRouteBuilder = new ProjectRouteBuilder();

        UserProjectRouteBuilder userProjectRouteBuilder = new UserProjectRouteBuilder();

        camelContext.addRoutes(userRouteBuilder);
        camelContext.addRoutes(projectRouteBuilder);
        camelContext.addRoutes(userProjectRouteBuilder);
        return camelContext;
    }
}
