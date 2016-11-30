package com.camel.pojos;

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
    private Timestamp dateCreateAccount;
    private Timestamp dateModificationAccount;
    private int status;
}
