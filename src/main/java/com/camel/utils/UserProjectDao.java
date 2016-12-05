package com.camel.utils;

import com.camel.dto.ProjectDto;
import com.camel.dto.UserProjectsDto;
import com.camel.tables.tables.UserProject;
import com.camel.tables.tables.records.UserProjectRecord;

import java.util.List;

public class UserProjectDao {
    public static UserProjectsDto getUserProjects(String idUser) {
        UserProjectsDto userProjectsDto = new UserProjectsDto();
        UserProject userProjectTable = UserProject.USER_PROJECT;
        List<UserProjectRecord> userProjectRecordList = UtilsDatabaseJooq.getDslContext().
                selectFrom(userProjectTable)
                .where(userProjectTable.USERS_ID_USER.equal(Integer.parseInt(idUser)))
                .fetch();

        userProjectsDto.setUserPojo(UserDao.getUser(idUser));
        for (UserProjectRecord userProjectRecord : userProjectRecordList) {
            ProjectDto projectDto = ProjectDao.getProject(userProjectRecord.getProjectsIdProject().toString());
            userProjectsDto.addProject(projectDto);
            userProjectsDto.setDateFrom(userProjectRecord.getDateFrom());
            userProjectsDto.setDateTo(userProjectRecord.getDateTo());
        }
        return userProjectsDto;
    }
}
