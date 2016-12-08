package com.camel.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserProjectsDTO {
    private UserDTO userDTO;
    private List<ProjectDTO> projectDTOS = new ArrayList<ProjectDTO>();
    private Date dateTo;
}
