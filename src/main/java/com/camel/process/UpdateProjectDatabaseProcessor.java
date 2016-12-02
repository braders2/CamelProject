package com.camel.process;

import com.camel.pojos.ProjectPojo;
import com.camel.utils.UtilsDatabaseMethods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateProjectDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();
        String projectJsonString = exchange.getIn().getBody(String.class);
        ProjectPojo projectPojo = gson.fromJson(projectJsonString, ProjectPojo.class);
        projectPojo.setIdProject(exchange.getIn().getHeader("id", Integer.class));
        String json = UtilsDatabaseMethods.updateProject(projectPojo);

        exchange.getOut().setHeader("Content-type", "application/json");
        exchange.getOut().setBody(json);
    }
}
