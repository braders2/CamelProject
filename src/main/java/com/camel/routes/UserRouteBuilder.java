package com.camel.routes;

import com.camel.utils.Const;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class UserRouteBuilder extends RouteBuilder {
    private final static String RESOURCE = "user/";


    public void configure() throws Exception {

        from(String.format("%s%s{username}%s", Const.URL, RESOURCE, Const.METHOD_GET))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange
                                .getOut()
                                .setBody("jacek");
                    }
                }).transform().body();

        from(String.format("%s%s%s", Const.URL, RESOURCE, Const.METHOD_POST))
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        exchange
                                .getOut()
                                .setBody(exchange.getIn()
                                        .getHeader(Exchange.HTTP_METHOD, String.class));
                    }
                }).transform().body();

    }
}
