package com.camel.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ProjectDto {
    private int idProject;
    private String name;
    @SerializedName("time_from")
    private Date timeFrom;
    @SerializedName("time_to")
    private Date timeTo;
    private CustomerDTO customerDTO;
}
