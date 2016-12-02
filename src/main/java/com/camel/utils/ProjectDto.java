package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.pojos.ProjectPojo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.camel.tables.tables.Project;
import com.camel.tables.tables.records.ProjectRecord;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 02.12.2016.
 */
public class ProjectDto {
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

    public static String getProject(String idProject) {
        Gson gson = new Gson();
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

        String resultJson = gson.toJson(projectPojo);
        return resultJson;
    }

    public static String getProjects() {
        Gson gson = new Gson();
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
        String resultJson = gson.toJson(projectPojos);
        return resultJson;
    }

    public static String deleteProject(String idProject) {
        Gson gson = new Gson();
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext()
                .fetchOne(project, project.ID_PROJECT.equal(Integer.valueOf(idProject)));
        int successDeleteRecord = projectRecord.delete();
        String resultJson = gson.toJson(successDeleteRecord);
        return resultJson;
    }

    public static String updateProject(ProjectPojo projectPojo) {
        Gson gson = new Gson();
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = UtilsDatabaseJooq.getDslContext().newRecord(project, projectPojo);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(projectRecord);
        return gson.toJson(successUpdateRecords);
    }
}
