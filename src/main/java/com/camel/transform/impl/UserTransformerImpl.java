package com.camel.transform.impl;

import com.camel.dto.UserDTO;
import com.camel.tables.tables.records.UserRecord;
import com.camel.transform.GenericTransformer;

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
        throw new UnsupportedOperationException("Convertin for ");
    }
}
