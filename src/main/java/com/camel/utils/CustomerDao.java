package com.camel.utils;

import com.camel.dto.CustomerDTO;
import com.camel.exceptions.JsonParserException;
// TODO: Replace this with line below!
import com.camel.tables.tables.Customer;
//import com.camel.model.tables.Customer;
// TODO: Replace this with line below!
import com.camel.tables.tables.records.CustomerRecord;
//import com.camel.model.tables.records.CustomerRecord;
import com.google.gson.JsonObject;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class);

    public static void insertCustomer(JsonObject jsonObject) {
        try {
            Customer customer = Customer.CUSTOMER;
            CustomerRecord customerRecord = UtilsDatabaseJooq.getDslContext().newRecord(customer);
            customerRecord.setName(jsonObject.get("firstname").getAsString());
            customerRecord.setDateCreated(new Timestamp(System.currentTimeMillis()));
            customerRecord.setContactPerson(jsonObject.get("firstname").getAsString());
            customerRecord.setContactEmail(jsonObject.get("email").getAsString());
            customerRecord.setIdStatus(jsonObject.get("id_status").getAsInt());
            customerRecord.store();
        } catch (NullPointerException exception) {
            LOGGER.error("Incorrect Json Data Format", exception);
            throw new JsonParserException();
        }
    }

    public static CustomerDTO getCustomer(String idCustomer) {
        try {
            CustomerDTO customerDTO = new CustomerDTO();
            Customer customer = Customer.CUSTOMER;

            CustomerRecord customerRecord = UtilsDatabaseJooq.getDslContext().
                    selectFrom(customer).
                    where(customer.ID.equal(Integer.parseInt(idCustomer)))
                    .fetchOne();

            customerDTO.setIdCustomer(customerRecord.getId());
            customerDTO.setName(customerRecord.getName());
            customerDTO.setDateCreated(customerRecord.getDateCreated());
            customerDTO.setContactPerson(customerRecord.getContactPerson());
            customerDTO.setContactEmail(customerRecord.getContactEmail());
            customerDTO.setIdCustomerStatus(customerRecord.getIdStatus());
            return customerDTO;
        } catch (NullPointerException exception) {
            LOGGER.error("The customer with that ID does not exist in database", exception);
            throw new DataAccessException("The customer with that ID does not exist in database");
        }
    }

    public static List<CustomerDTO> getCustomers() {
        try {
            Customer customer = Customer.CUSTOMER;
            List<CustomerDTO> customerDTOS = new ArrayList<>();
            List<CustomerRecord> customerRecords = UtilsDatabaseJooq.getDslContext()
                    .selectFrom(customer)
                    .fetch();
            for (CustomerRecord customerRecord : customerRecords) {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setIdCustomer(customerRecord.getId());
                customerDTO.setName(customerRecord.getName());
                customerDTO.setDateCreated(customerRecord.getDateCreated());
                customerDTO.setContactPerson(customerRecord.getContactPerson());
                customerDTO.setContactEmail(customerRecord.getContactEmail());
                customerDTO.setIdCustomerStatus(customerRecord.getIdStatus());
                customerDTOS.add(customerDTO);
            }
            return customerDTOS;
        } catch (NullPointerException exception) {
            LOGGER.error("Database dont't have customers", exception);
            throw new DataAccessException("Database dont't have customers");
        }
    }

    public static void deleteCustomer(String idCustomer) {
        Customer customer = Customer.CUSTOMER;
        CustomerRecord customerRecord = UtilsDatabaseJooq.getDslContext().fetchOne(customer, customer.ID.equal(Integer.valueOf(idCustomer)));
        int successDeleteRecord = customerRecord.delete();
        if (successDeleteRecord == 0) {
            LOGGER.error("The customer with that ID does not exist in database");
            throw new DataAccessException("The customer with that ID does not exist in database");
        }
    }

    public static void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = Customer.CUSTOMER;
        CustomerRecord customerRecord = UtilsDatabaseJooq.getDslContext().newRecord(customer, customerDTO);
        int successUpdateRecords = UtilsDatabaseJooq.getDslContext().executeUpdate(customerRecord);
        if (successUpdateRecords == 0) {
            LOGGER.error("The customer with that ID does not exist in database");
            throw new DataAccessException("The customer with that ID does not exist in database");
        }
    }

}
