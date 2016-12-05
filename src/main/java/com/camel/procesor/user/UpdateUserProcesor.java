package com.camel.procesor.user;

import com.camel.dto.UserDto;
import com.camel.utils.UserDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class UpdateUserProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        String userJsonString = exchange.getIn().getBody(String.class);
        UserDto userDto = gson.fromJson(userJsonString, UserDto.class);
        userDto.setIdUser(exchange.getIn().getHeader("id", Integer.class));
        UserDao.updateUser(userDto);
    }
}
