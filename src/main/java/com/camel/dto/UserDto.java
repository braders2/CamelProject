package com.camel.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class UserDto {
    private int idUser;
    private String firstname;
    private String surname;
    private String email;
    @SerializedName("data_create_account")
    private Timestamp dataCreateAccount;
    @SerializedName("data_modification_account")
    private Timestamp dataModificationAccount;
    private byte status;
}
