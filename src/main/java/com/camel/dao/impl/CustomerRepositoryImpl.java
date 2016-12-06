package com.camel.dao.impl;

import com.camel.dao.CustomerRepository;
import com.camel.tables.tables.Customer;
import com.camel.tables.tables.records.CustomerRecord;
import com.camel.utils.UtilsDatabaseJooq;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.Customer.CUSTOMER;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Optional<CustomerRecord> get(Long aLong) {
        return UtilsDatabaseJooq.getDslContext()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.equal(aLong.intValue()))
                .fetchOptional();
    }

    @Override
    public Collection<CustomerRecord> getAll() {
        return UtilsDatabaseJooq.getDslContext()
                .selectFrom(Customer.CUSTOMER)
                .fetch();
    }

    @Override
    public boolean update(CustomerRecord entity) {
        return UtilsDatabaseJooq.getDslContext()
                .update(CUSTOMER)
                .set(CUSTOMER.NAME, entity.getName())
                .set(CUSTOMER.DATE_CREATED, entity.getDateCreated())
                .set(CUSTOMER.CONTACT_PERSON, entity.getContactPerson())
                .set(CUSTOMER.CONTACT_EMAIL, entity.getContactEmail())
                .set(CUSTOMER.ID_STATUS, entity.getIdStatus())
                .execute() != 0;
    }

    @Override
    public boolean insert(CustomerRecord entity) {
        return UtilsDatabaseJooq.getDslContext()
                .insertInto(CUSTOMER)
                .set(CUSTOMER.NAME, entity.getName())
                .set(CUSTOMER.DATE_CREATED, entity.getDateCreated())
                .set(CUSTOMER.CONTACT_PERSON, entity.getContactPerson())
                .set(CUSTOMER.CONTACT_EMAIL, entity.getContactEmail())
                .set(CUSTOMER.ID_STATUS, entity.getIdStatus())
                .execute() != 0;
    }

    @Override
    public boolean delete(Long aLong) {
        return UtilsDatabaseJooq.getDslContext()
                .delete(CUSTOMER)
                .where(CUSTOMER.ID.equal(aLong.intValue()))
                .execute() != 0;
    }

    @Override
    public boolean deleteAll() {
        return UtilsDatabaseJooq.getDslContext()
                .delete(CUSTOMER)
                .execute() != 0;
    }
}
