package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.UtilsDatabaseJooq;
import org.jooq.DSLContext;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.User.USER;

public class UserRepositoryImpl implements UserRepository {

    private DSLContext dslContext;

    public UserRepositoryImpl() {
        dslContext = UtilsDatabaseJooq.getDslContext();
    }

    @Override
    public Optional<UserRecord> get(Long aLong) {
        Optional<UserRecord> userRecordOptional = dslContext.selectFrom(USER)
                                                            .where(USER.ID_USER.equal(aLong.intValue()))
                                                            .fetchOptional();
        dslContext.close();
        return userRecordOptional;
    }

    @Override
    public Collection<UserRecord> getAll() {
        Collection<UserRecord> userRecordCollection = dslContext.selectFrom(USER)
                .fetch();
        dslContext.close();
        return userRecordCollection;
    }

    @Override
    public boolean update(UserRecord entity) {
        int updatedRecord = dslContext.executeUpdate(entity);
        dslContext.close();
        return updatedRecord != 0;
    }

    @Override
    public boolean insert(UserRecord entity) {
        int insertedRecord = dslContext.executeInsert(entity);
        dslContext.close();
        return insertedRecord != 0;

    }

    @Override
    public boolean delete(Long aLong) {
        int deletedRecord = dslContext.deleteFrom(USER)
                                      .where(USER.ID_USER.equal(aLong.intValue()))
                                      .execute();
        dslContext.close();
        return deletedRecord != 0;
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Method will be implement later");
    }
}
