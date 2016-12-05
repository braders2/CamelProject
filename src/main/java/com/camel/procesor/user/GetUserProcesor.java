package com.camel.procesor.user;

import com.camel.dto.UserDto;
import com.camel.utils.UserDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUserProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        UserDto userDto = UserDao.getUser(exchange.getIn()
                .getHeader("id").toString());
    }
}
