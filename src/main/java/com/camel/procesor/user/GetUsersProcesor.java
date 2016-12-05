package com.camel.procesor.user;

import com.camel.dto.UserDto;
import com.camel.utils.UserDao;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class GetUsersProcesor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Gson gson = new Gson();
        List<UserDto> userDtos = UserDao.getUsers();
    }
}
