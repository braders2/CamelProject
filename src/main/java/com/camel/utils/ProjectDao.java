package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.dto.ProjectDto;
import com.camel.tables.tables.Project;
import com.camel.tables.tables.records.ProjectRecord;
import com.google.gson.JsonObject;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {
    private final static Logger logger = LoggerFactory.getLogger(ProjectDao.class);

    public static void insertProject(JsonObject jsonObject) {
        try {
            Project project = Project.PROJECT;
            ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().newRecord(project);
            projectRecord.setName(jsonObject.get("project_name").getAsString());
            projectRecord.setTimeFrom(Date.valueOf(jsonObject.get("time_from").getAsString()));
            projectRecord.setTimeTo(Date.valueOf(jsonObject.get("time_to").getAsString()));
            projectRecord.store();
        } catch (NullPointerException exception) {
            throw new JsonParserException();
        }
    }

    public static ProjectDto getProject(String idProject) {
        try {
            ProjectDto projectDto = new ProjectDto();
            Project project = Project.PROJECT;
            ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project).
                    where(project.ID_PROJECT.equal(Integer.parseInt(idProject)))
                    .fetchOne();

            projectDto.setIdProject(projectRecord.getIdProject());
            projectDto.setName(projectRecord.getName());
            projectDto.setTimeFrom(projectRecord.getTimeFrom());
            projectDto.setTimeTo(projectRecord.getTimeTo());
            return projectDto;
        } catch (NullPointerException exception) {
            throw new DataAccessException("The project with that ID does not exist in database");
        }
    }

    public static List<ProjectDto> getProjects() {
        try {
            Project project = Project.PROJECT;
            List<ProjectDto> projectDtos = new ArrayList<ProjectDto>();
            List<ProjectRecord> projectRecords = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project)
                    .fetch();
            for (ProjectRecord projectRecord : projectRecords) {
                ProjectDto projectDto = new ProjectDto();
                projectDto.setIdProject(projectRecord.getIdProject());
                projectDto.setName(projectRecord.getName());
                projectDto.setTimeFrom(projectRecord.getTimeFrom());
                projectDto.setTimeTo(projectRecord.getTimeTo());
                projectDtos.add(projectDto);
            }
            return projectDtos;
        } catch (NullPointerException exception) {
            throw new DataAccessException("Database don't have projects");
        }
    }

    public static void deleteProject(String idProject) {
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext()
                .fetchOne(project, project.ID_PROJECT.equal(Integer.valueOf(idProject)));
        int successDeleteRecord = projectRecord.delete();
        if (successDeleteRecord == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

    public static void updateProject(ProjectDto projectDto) {
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().newRecord(project, projectDto);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(projectRecord);
        if (successUpdateRecords == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }
}
