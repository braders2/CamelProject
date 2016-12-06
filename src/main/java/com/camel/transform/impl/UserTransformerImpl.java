package com.camel.transform.impl;

import com.camel.dto.UserDTO;
import com.camel.tables.tables.User;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.GenericTransformer;
import com.camel.utils.UtilsDatabaseJooq;

public class UserTransformerImpl implements GenericTransformer<UserDTO, UserRecord> {

    @Override
    public UserDTO convertToDto(UserRecord userRecord) {
        return UserDTO.builder()
                .idUser(userRecord.getIdUser())
                .firstname(userRecord.getFirstname())
                .surname(userRecord.getSurname())
                .email(userRecord.getEmail())
                .dataCreateAccount(userRecord.getDataCreateAccount())
                .dataModificationAccount(userRecord.getDataModificationAccount())
                .status(userRecord.getStatus())
                .build();
    }

    @Override
    public UserRecord convertToEntity(UserDTO userDTO) {
        UserRecord userRecord = new UserRecord();
        userRecord.setIdUser(userDTO.getIdUser());
        userRecord.setFirstname(userDTO.getFirstname());
        userRecord.setSurname(userDTO.getSurname());
        userRecord.setEmail(userDTO.getEmail());
        userRecord.setDataCreateAccount(userDTO.getDataCreateAccount());
        userRecord.setDataModificationAccount(userDTO.getDataModificationAccount());
        userRecord.setStatus(userDTO.getStatus());
        return userRecord;
    }
}
