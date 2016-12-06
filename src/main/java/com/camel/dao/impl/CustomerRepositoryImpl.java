package com.camel.dao.impl;

import com.camel.dao.CustomerRepository;
import com.camel.tables.tables.Customer;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.utils.UtilsDatabaseJooq;
import org.jooq.DSLContext;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.Customer.CUSTOMER;

public class CustomerRepositoryImpl implements CustomerRepository {

    private DSLContext dslContext;

    public CustomerRepositoryImpl() {
        dslContext = UtilsDatabaseJooq.getDslContext();
    }

    @Override
    public Optional<CustomerRecord> get(Long aLong) {

        Optional<CustomerRecord> customerRecord =  dslContext
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.equal(aLong.intValue()))
                .fetchOptional();
        dslContext.close();

        return customerRecord;
    }

    @Override
    public Collection<CustomerRecord> getAll() {
        Collection<CustomerRecord> customerRecords = dslContext
                .selectFrom(Customer.CUSTOMER)
                .fetch();
        dslContext.close();
        return customerRecords;
    }

    @Override
    public boolean update(CustomerRecord entity) {
        int updatedRecord = dslContext
                .update(CUSTOMER)
                .set(CUSTOMER.NAME, entity.getName())
                .set(CUSTOMER.DATE_CREATED, entity.getDateCreated())
                .set(CUSTOMER.CONTACT_PERSON, entity.getContactPerson())
                .set(CUSTOMER.CONTACT_EMAIL, entity.getContactEmail())
                .set(CUSTOMER.ID_STATUS, entity.getIdStatus())
                .execute();
        return updatedRecord != 0;
    }

    @Override
    public boolean insert(CustomerRecord entity) {
        int insertedRecord = dslContext
                .insertInto(CUSTOMER)
                .set(CUSTOMER.NAME, entity.getName())
                .set(CUSTOMER.DATE_CREATED, entity.getDateCreated())
                .set(CUSTOMER.CONTACT_PERSON, entity.getContactPerson())
                .set(CUSTOMER.CONTACT_EMAIL, entity.getContactEmail())
                .set(CUSTOMER.ID_STATUS, entity.getIdStatus())
                .execute();
        return insertedRecord != 0;
    }

    @Override
    public boolean delete(Long aLong) {
        int deletedRecord =  dslContext
                .delete(CUSTOMER)
                .where(CUSTOMER.ID.equal(aLong.intValue()))
                .execute();
        return deletedRecord != 0;
    }

    @Override
    public boolean deleteAll() {
        int deletedRecords = dslContext
                .delete(CUSTOMER)
                .execute();
        return deletedRecords != 0;
    }
}
