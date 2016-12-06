package com.camel.procesor;

import com.camel.dto.UserProjectsDto;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GetUserProjectProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        UserProjectsDto userProjectsDto = UserProjectDao.getUserProjects(exchange.getIn()
                                            .getHeader("id").toString());
    }
}
