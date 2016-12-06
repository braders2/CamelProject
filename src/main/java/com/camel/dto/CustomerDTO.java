package com.camel.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class CustomerDTO {
    private int idCustomer;
    private String name;
    @SerializedName("date_created")
    private Timestamp dateCreated;
    private String contactPerson;
    private String contactEmail;
    @SerializedName("customer_status")
    private int idCustomerStatus;
}
