package com.camel.process;

import com.camel.utils.UserDto;
import com.camel.utils.UserProjectDto;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserProjectFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String userProjectsJson = UserProjectDto.getUserProjects(exchange.getIn()
                                            .getHeader("id").toString());
        exchange.getOut().setHeader("Content-type", "application/json");
        exchange.getOut().setBody(userProjectsJson);
    }
}
