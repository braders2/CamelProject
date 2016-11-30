package com.camel.pojos;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
@Getter
@Setter
public class UserProjectsPojo {
    @SerializedName("user")
    private UserPojo userPojo;
    @SerializedName("list_projects")
    private List<ProjectPojo> projectPojos = new ArrayList<ProjectPojo>();
    @SerializedName("data_from")
    private Date dateFrom;
    @SerializedName("data_to")
    private Date dateTo;

    public void addProject(ProjectPojo projectPojo) {
        projectPojos.add(projectPojo);
    }
}
