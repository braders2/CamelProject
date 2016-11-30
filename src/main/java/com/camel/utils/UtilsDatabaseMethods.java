package com.camel.utils;

import com.camel.pojos.UserPojo;
import com.camel.tables.tables.User;
import com.camel.tables.tables.records.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Timestamp;

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
        User user = User.USER;
        UserRecord userRecord = getDslContext().newRecord(user);
        userRecord.setFirstname(jsonObject.get("firstname").getAsString());
        userRecord.setSurname(jsonObject.get("surname").getAsString());
        userRecord.setEmail(jsonObject.get("email").getAsString());
        userRecord.setDataCreateAccount(new Timestamp(System.currentTimeMillis()));
        userRecord.setStatus(jsonObject.get("status").getAsByte());
        userRecord.store();
    }

    public static String getUser(String idUser) {
        Gson gson = new Gson();
        UserPojo userPojo = new UserPojo();
        User user = User.USER;
        UserRecord userRecord = getDslContext().
                selectFrom(user).
                where(user.ID_USER.equal(Integer.parseInt(idUser)))
                .fetchOne();

        userPojo.setIdUser(userRecord.getIdUser());
        userPojo.setFirstname(userRecord.getFirstname());
        userPojo.setSurname(userRecord.getSurname());
        userPojo.setEmail(userRecord.getEmail());
        userPojo.setDateCreateAccount(userRecord.getDataCreateAccount());
        userPojo.setDateModificationAccount(userRecord.getDataModificationAccount());
        userPojo.setStatus(userRecord.getStatus());
        String resultJson = gson.toJson(userPojo);
        return resultJson;
    }

    public static String updateUser(UserPojo userPojo) {
        Gson gson = new Gson();
        User user = User.USER;
        UserRecord userRecord = getDslContext().newRecord(user, userPojo);
        int successUpdateRecords = getDslContext().executeUpdate(userRecord);
        return gson.toJson(successUpdateRecords);
    }
}
