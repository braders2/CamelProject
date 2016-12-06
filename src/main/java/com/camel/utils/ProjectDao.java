package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.dto.ProjectDTO;
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

    public static ProjectDTO getProject(String idProject) {
        try {
            ProjectDTO projectDTO = new ProjectDTO();
            Project project = Project.PROJECT;
            ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project).
                    where(project.ID_PROJECT.equal(Integer.parseInt(idProject)))
                    .fetchOne();

            projectDTO.setIdProject(projectRecord.getIdProject());
            projectDTO.setName(projectRecord.getName());
            projectDTO.setTimeFrom(projectRecord.getTimeFrom());
            projectDTO.setTimeTo(projectRecord.getTimeTo());
            return projectDTO;
        } catch (NullPointerException exception) {
            throw new DataAccessException("The project with that ID does not exist in database");
        }
    }

    public static List<ProjectDTO> getProjects() {
        try {
            Project project = Project.PROJECT;
            List<ProjectDTO> projectDTOS = new ArrayList<ProjectDTO>();
            List<ProjectRecord> projectRecords = UtilsDatabaseJooq.getDslContext().
                    selectFrom(project)
                    .fetch();
            for (ProjectRecord projectRecord : projectRecords) {
                ProjectDTO projectDTO = new ProjectDTO();
                projectDTO.setIdProject(projectRecord.getIdProject());
                projectDTO.setName(projectRecord.getName());
                projectDTO.setTimeFrom(projectRecord.getTimeFrom());
                projectDTO.setTimeTo(projectRecord.getTimeTo());
                projectDTOS.add(projectDTO);
            }
            return projectDTOS;
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

    public static void updateProject(ProjectDTO projectDTO) {
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().newRecord(project, projectDTO);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(projectRecord);
        if (successUpdateRecords == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }
}
