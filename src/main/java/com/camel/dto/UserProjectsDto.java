package com.camel.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserProjectsDto {
    @SerializedName("user")
    private UserDto userDto;
    @SerializedName("list_projects")
    private List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
    @SerializedName("data_from")
    private Date dateFrom;
    @SerializedName("data_to")
    private Date dateTo;

    public void addProject(ProjectDto projectDto) {
        projectDtos.add(projectDto);
    }
}
