package com.camel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerStatusDTO {
    private int idCustomerStatus;
    private String name;
    private int sortOrder;
}
