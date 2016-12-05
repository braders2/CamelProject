package com.camel.pojos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserProjectsPojo {
    private ProjectPojo project;
    private Date dateFrom;
    private Date dateTo;

}
