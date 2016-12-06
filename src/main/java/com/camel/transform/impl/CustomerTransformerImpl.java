package com.camel.transform.impl;

import com.camel.dto.CustomerDTO;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.transform.GenericTransformer;

public class CustomerTransformerImpl implements GenericTransformer<CustomerDTO, CustomerRecord> {
    @Override
    public CustomerDTO convertToDto(CustomerRecord customerRecord) {
        return CustomerDTO.builder()
                .idCustomer(customerRecord.getId())
                .name(customerRecord.getName())
                .dateCreated(customerRecord.getDateCreated())
                .contactPerson(customerRecord.getContactPerson())
                .contactEmail(customerRecord.getContactEmail())
                .idCustomerStatus(customerRecord.getIdStatus())
                .build();
    }

    @Override
    public CustomerRecord convertToEntity(CustomerDTO customerDTO) {
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setId(customerDTO.getIdCustomer());
        customerRecord.setName(customerDTO.getName());
        customerRecord.setDateCreated(customerDTO.getDateCreated());
        customerRecord.setContactPerson(customerDTO.getContactPerson());
        customerRecord.setContactEmail(customerDTO.getContactEmail());
        customerRecord.setIdStatus(customerDTO.getIdCustomerStatus());
        return customerRecord;
    }
}
