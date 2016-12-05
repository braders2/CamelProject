package com.camel.utils;

import com.camel.exceptions.JsonParserException;
import com.camel.dto.UserDto;
import com.camel.tables.tables.User;
import com.camel.tables.tables.records.UserRecord;
import com.google.gson.JsonObject;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

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
            LOGGER.error("Incorrect Json Data Format", exception);
            throw new JsonParserException();
        }
    }

    public static UserDto getUser(String idUser) {
        try {
            UserDto userDto = new UserDto();
            User user = User.USER;

            UserRecord userRecord = UtilsDatabaseJooq.getDslContext().
                    selectFrom(user).
                    where(user.ID_USER.equal(Integer.parseInt(idUser)))
                    .fetchOne();

            userDto.setIdUser(userRecord.getIdUser());
            userDto.setFirstname(userRecord.getFirstname());
            userDto.setSurname(userRecord.getSurname());
            userDto.setEmail(userRecord.getEmail());
            userDto.setDataCreateAccount(userRecord.getDataCreateAccount());
            userDto.setDataModificationAccount(userRecord.getDataModificationAccount());
            userDto.setStatus(userRecord.getStatus());
            return userDto;
        } catch (NullPointerException exception) {
            LOGGER.error("The user with that ID does not exist in database", exception);
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

    public static List<UserDto> getUsers() {
        try {
            User user = User.USER;
            List<UserDto> userDtos = new ArrayList<UserDto>();
            List<UserRecord> userRecords = UtilsDatabaseJooq.getDslContext().
                    selectFrom(user)
                    .fetch();
            for (UserRecord userRecord : userRecords) {
                UserDto userDto = new UserDto();
                userDto.setIdUser(userRecord.getIdUser());
                userDto.setFirstname(userRecord.getFirstname());
                userDto.setSurname(userRecord.getSurname());
                userDto.setEmail(userRecord.getEmail());
                userDto.setDataCreateAccount(userRecord.getDataCreateAccount());
                userDto.setDataModificationAccount(userRecord.getDataModificationAccount());
                userDto.setStatus(userRecord.getStatus());
                userDtos.add(userDto);
            }
            return userDtos;
        } catch (NullPointerException exception) {
            LOGGER.error("Database dont't have users", exception);
            throw new DataAccessException("Database dont't have users");
        }
    }

    public static void deleteUser(String idUser) {
        User user = User.USER;
        UserRecord userRecord = UtilsDatabaseJooq.getDslContext().fetchOne(user, user.ID_USER.equal(Integer.valueOf(idUser)));
        int successDeleteRecord = userRecord.delete();
        if (successDeleteRecord == 0) {
            LOGGER.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

    public static void updateUser(UserDto userDto) {
        User user = User.USER;
        UserRecord userRecord = UtilsDatabaseJooq.getDslContext().newRecord(user, userDto);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(userRecord);
        if (successUpdateRecords == 0) {
            LOGGER.error("The user with that ID does not exist in database");
            throw new DataAccessException("The user with that ID does not exist in database");
        }
    }

}
