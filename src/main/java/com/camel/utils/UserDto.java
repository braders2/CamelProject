package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.pojos.UserPojo;
import com.camel.tables.tables.User;
import com.camel.tables.tables.records.UserRecord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Dobrowolski on 30.11.2016.
 */
public class UserDto {
    private final static Logger logger = LoggerFactory.getLogger(UserDto.class);

    public static void insertUser(JsonObject jsonObject) {
        try {
            User user = User.USER;
            UserRecord userRecord = UtilsDatabaseJooq.getDslContext().newRecord(user);
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

    public static UserPojo getUser(String idUser) {
        try {
            UserPojo userPojo = new UserPojo();
            User user = User.USER;

            UserRecord userRecord = UtilsDatabaseJooq.getDslContext().
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
            return userPojo;
        } catch (NullPointerException exception) {
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

    public static List<UserPojo> getUsers() {
        try {
            Gson gson = new Gson();
            User user = User.USER;
            List<UserPojo> userPojos = new ArrayList<UserPojo>();
            List<UserRecord> userRecords = UtilsDatabaseJooq.getDslContext().
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
            return userPojos;
        } catch (NullPointerException exception) {
            throw new DataAccessException("Database dont't have users");
        }
    }

    public static void deleteUser(String idUser) {
        User user = User.USER;
        UserRecord userRecord = UtilsDatabaseJooq.getDslContext().fetchOne(user, user.ID_USER.equal(Integer.valueOf(idUser)));
        int successDeleteRecord = userRecord.delete();
        if (successDeleteRecord == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

    public static void updateUser(UserPojo userPojo) {
        Gson gson = new Gson();
        User user = User.USER;
        UserRecord userRecord = UtilsDatabaseJooq.getDslContext().newRecord(user, userPojo);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(userRecord);
        if (successUpdateRecords == 0) {
            logger.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

}
