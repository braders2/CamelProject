package com.camel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@Builder
public class UserDTO {
    private int idUser;
    private String firstname;
    private String surname;
    private String email;
    private Timestamp dataCreateAccount;
    private Timestamp dataModificationAccount;
    private byte status;
}
