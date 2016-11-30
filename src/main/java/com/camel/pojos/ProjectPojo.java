package com.camel.pojos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
public class ProjectPojo {
    private int idProject;
    private String name;
    private Date timeFrom;
    private Date timeTo;
}
