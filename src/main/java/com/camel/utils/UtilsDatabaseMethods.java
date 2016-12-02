package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.pojos.ProjectPojo;
import com.camel.pojos.UserPojo;
import com.camel.pojos.UserProjectsPojo;
import com.camel.tables.tables.Project;
import com.camel.tables.tables.User;
import com.camel.tables.tables.UserProject;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.tables.tables.records.UserProjectRecord;
import com.camel.tables.tables.records.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class UtilsDatabaseMethods {
    public static DSLContext dslContext;

    public static DSLContext getDslContext() {
        if (dslContext == null) {
            dslContext = DSL.using(UtilsDatabaseJooq.connection, SQLDialect.MYSQL);
        }
        return dslContext;
    }

    public static void insertUser(JsonObject jsonObject) {
        try {
            User user = User.USER;
            UserRecord userRecord = getDslContext().newRecord(user);
            userRecord.setFirstname(jsonObject.get("firstname").getAsString());
            userRecord.setSurname(jsonObject.get("surname").getAsString());
            userRecord.setEmail(jsonObject.get("email").getAsString());
            userRecord.setDataCreateAccount(new Timestamp(System.currentTimeMillis()));
            userRecord.setStatus(jsonObject.get("status").getAsByte());
            userRecord.store();
        } catch (NullPointerException exception) {
            throw new JsonParserException();
        }
    }

    public static String getUser(String idUser) {
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = User.USER;
        try {
            UserRecord userRecord = getDslContext().
                    selectFrom(user).
                    where(user.ID_USER.equal(Integer.parseInt(idUser)))
                    .fetchOne();

            userPojo.setIdUser(userRecord.getIdUser());
            userPojo.setFirstname(userRecord.getFirstname());
            userPojo.setSurname(userRecord.getSurname());
            userPojo.setEmail(userRecord.getEmail());
            userPojo.setDataCreateAccount(userRecord.getDataCreateAccount());
            userPojo.setDataModificationAccount(userRecord.getDataModificationAccount());
            userPojo.setStatus(userRecord.getStatus());
            String resultJson = gson.toJson(userPojo);
            return resultJson;
        } catch (NullPointerException exception) {
            return Const.EMPTY_STRING;
        }
    }

    public static String getUsers() {
        Gson gson = new Gson();
        User user = User.USER;
        List<UserPojo> userPojos = new ArrayList<UserPojo>();
        List<UserRecord> userRecords = getDslContext().
                selectFrom(user)
                .fetch();
        for (UserRecord userRecord : userRecords) {
            UserPojo userPojo = new UserPojo();
            userPojo.setIdUser(userRecord.getIdUser());
            userPojo.setFirstname(userRecord.getFirstname());
            userPojo.setSurname(userRecord.getSurname());
            userPojo.setEmail(userRecord.getEmail());
            userPojo.setDataCreateAccount(userRecord.getDataCreateAccount());
            userPojo.setDataModificationAccount(userRecord.getDataModificationAccount());
            userPojo.setStatus(userRecord.getStatus());
            userPojos.add(userPojo);
        }
        String resultJson = gson.toJson(userPojos);
        return resultJson;
    }

    public static String deleteUser(String idUser) {
        Gson gson = new Gson();
        User user = User.USER;
        UserRecord userRecord = getDslContext().fetchOne(user, user.ID_USER.equal(Integer.valueOf(idUser)));
        int successDeleteRecords = userRecord.delete();
        String resultJson = gson.toJson(successDeleteRecords);
        return resultJson;
    }

    public static String updateUser(UserPojo userPojo) {
        Gson gson = new Gson();
        User user = User.USER;
        UserRecord userRecord = getDslContext().newRecord(user, userPojo);
        int successUpdateRecords = getDslContext().executeUpdate(userRecord);
        return gson.toJson(successUpdateRecords);
    }

    public static void insertProject(JsonObject jsonObject) {
        try {
            Project project = Project.PROJECT;
            ProjectRecord projectRecord = getDslContext().newRecord(project);
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
        ProjectRecord projectRecord = getDslContext().
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
        List<ProjectRecord> projectRecords = getDslContext().
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
        ProjectRecord projectRecord = getDslContext()
                .fetchOne(project, project.ID_PROJECT.equal(Integer.valueOf(idProject)));
        int successDeleteRecord = projectRecord.delete();
        String resultJson = gson.toJson(successDeleteRecord);
        return resultJson;
    }

    public static String updateProject(ProjectPojo projectPojo) {
        Gson gson = new Gson();
        Project project = Project.PROJECT;
        ProjectRecord projectRecord = getDslContext().newRecord(project, projectPojo);
        int successUpdateRecords = getDslContext().executeUpdate(projectRecord);
        return gson.toJson(successUpdateRecords);
    }

    public static String getUserProjects(String idUser) {
        Gson gson = new Gson();
        UserProjectsPojo userProjectsPojo = new UserProjectsPojo();
        UserProject userProjectTable = UserProject.USER_PROJECT;
        List<UserProjectRecord> userProjectRecordList = getDslContext().
                selectFrom(userProjectTable)
                .where(userProjectTable.USERS_ID_USER.equal(Integer.parseInt(idUser)))
                .fetch();

        userProjectsPojo.setUserPojo(gson.fromJson(getUser(idUser), UserPojo.class));
        for (UserProjectRecord userProjectRecord : userProjectRecordList) {
            ProjectPojo projectPojo = gson.fromJson(getProject(userProjectRecord.getProjectsIdProject().toString()), ProjectPojo.class);
            userProjectsPojo.addProject(projectPojo);
            userProjectsPojo.setDateFrom(userProjectRecord.getDateFrom());
            userProjectsPojo.setDateTo(userProjectRecord.getDateTo());
        }
        String resultJson = gson.toJson(userProjectsPojo);
        return resultJson;
    }
}
