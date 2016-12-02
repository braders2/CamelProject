package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.pojos.ProjectPojo;
import com.camel.tables.tables.Project;
import com.camel.tables.tables.records.ProjectRecord;
import com.google.gson.JsonObject;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 02.12.2016.
 */
public class ProjectDto {
    private final static Logger logger = LoggerFactory.getLogger(ProjectDto.class);

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

    public static ProjectPojo getProject(String idProject) {
        try {
            ProjectPojo projectPojo = new ProjectPojo();
            Project project = Project.PROJECT;
            ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project).
                    where(project.ID_PROJECT.equal(Integer.parseInt(idProject)))
                    .fetchOne();

            projectPojo.setIdProject(projectRecord.getIdProject());
            projectPojo.setName(projectRecord.getName());
            projectPojo.setTimeFrom(projectRecord.getTimeFrom());
            projectPojo.setTimeTo(projectRecord.getTimeTo());
            return projectPojo;
        } catch (NullPointerException exception) {
            throw new DataAccessException("The project with that ID does not exist in database");
        }
    }

    public static List<ProjectPojo> getProjects() {
        try {
            Project project = Project.PROJECT;
            List<ProjectPojo> projectPojos = new ArrayList<ProjectPojo>();
            List<ProjectRecord> projectRecords = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project)
                    .fetch();
            for (ProjectRecord projectRecord : projectRecords) {
                ProjectPojo projectPojo = new ProjectPojo();
                projectPojo.setIdProject(projectRecord.getIdProject());
                projectPojo.setName(projectRecord.getName());
                projectPojo.setTimeFrom(projectRecord.getTimeFrom());
                projectPojo.setTimeTo(projectRecord.getTimeTo());
                projectPojos.add(projectPojo);
            }
            return projectPojos;
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

    public static void updateProject(ProjectPojo projectPojo) {
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().newRecord(project, projectPojo);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(projectRecord);
        if (successUpdateRecords == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }
}
