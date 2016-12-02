package com.camel.utils;

import com.camel.pojos.ProjectPojo;
import com.camel.pojos.UserProjectsPojo;
import com.camel.tables.tables.UserProject;
import com.camel.tables.tables.records.UserProjectRecord;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 02.12.2016.
 */
public class UserProjectDto {
    public static UserProjectsPojo getUserProjects(String idUser) {
        Gson gson = new Gson();
        UserProjectsPojo userProjectsPojo = new UserProjectsPojo();
        UserProject userProjectTable = UserProject.USER_PROJECT;
        List<UserProjectRecord> userProjectRecordList = UtilsDatabaseJooq.getDslContext().
                selectFrom(userProjectTable)
                .where(userProjectTable.USERS_ID_USER.equal(Integer.parseInt(idUser)))
                .fetch();

        userProjectsPojo.setUserPojo(UserDto.getUser(idUser));
        for (UserProjectRecord userProjectRecord : userProjectRecordList) {
            ProjectPojo projectPojo = ProjectDto.getProject(userProjectRecord.getProjectsIdProject().toString());
            userProjectsPojo.addProject(projectPojo);
            userProjectsPojo.setDateFrom(userProjectRecord.getDateFrom());
            userProjectsPojo.setDateTo(userProjectRecord.getDateTo());
        }
        return userProjectsPojo;
    }
}
