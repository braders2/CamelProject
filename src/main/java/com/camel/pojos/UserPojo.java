package com.camel.pojos;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class UserPojo {
    private int idUser;
    private String firstname;
    private String surname;
    private String email;
    @SerializedName("data_create_account")
    private Timestamp dataCreateAccount;
    @SerializedName("data_modification_account")
    private Timestamp dataModificationAccount;
    private int status;
}
