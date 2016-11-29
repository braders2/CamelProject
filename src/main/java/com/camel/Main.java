package com.camel;

import com.camel.routes.UserRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {
    public static void main(String args[]) throws Exception {

        CamelContext camelContext = initializeCamel();
        camelContext.start();
        Thread.sleep(10000000);
        camelContext.stop();

    }

    public static CamelContext initializeCamel() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        UserRouteBuilder testRouteBuilder = new UserRouteBuilder();

        camelContext.addRoutes(testRouteBuilder);
        return camelContext;
    }
}
