package com.camel.procesor.project;

import com.camel.utils.ProjectDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class DeleteProjectProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        ProjectDao.deleteProject(exchange.getIn().getHeader("id").toString());
    }
}
