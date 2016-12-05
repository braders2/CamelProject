package com.camel.pojos;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

/**
 * Created by Albert on 05.12.2016.
 */
public class CustomerPojo {
    private int idCustomer;
    private String name;
    @SerializedName("date_created")
    private Timestamp dateCreated;
    private String contactPerson;
    private String contactEmail;
    @SerializedName("customer_status")
    private CustomerStatusPojo customerStatusPojo;
}
