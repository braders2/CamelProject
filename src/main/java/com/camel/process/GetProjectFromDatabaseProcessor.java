package com.camel.process;

import com.camel.utils.ProjectDto;
import com.camel.utils.UserDto;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetProjectFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String projectJson = ProjectDto.getProject(exchange.getIn()
                .getHeader("id").toString());
        exchange.getOut().setHeader("Content-type", "application/json");
        exchange.getOut().setBody(projectJson);
    }
}
