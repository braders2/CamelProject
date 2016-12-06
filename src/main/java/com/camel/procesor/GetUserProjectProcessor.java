package com.camel.procesor;

import com.camel.dto.UserProjectsDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserProjectProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        UserProjectsDto userProjectsDto = UserProjectDao.getUserProjects(exchange.getIn()
                                            .getHeader("id").toString());
    }
}
