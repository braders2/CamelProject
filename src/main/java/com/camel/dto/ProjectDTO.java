package com.camel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
public class ProjectDTO {
    private int idProject;
    private String name;
    private Date timeFrom;
    private Date timeTo;
}
