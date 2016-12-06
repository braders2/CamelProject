package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.UtilsDatabaseJooq;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.User.USER;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<UserRecord> get(Long aLong) {
        return UtilsDatabaseJooq.getDslContext()
                .selectFrom(USER)
                .where(USER.ID_USER.equal(aLong.intValue()))
                .fetchOptional();
    }

    @Override
    public Collection<UserRecord> getAll() {
        return UtilsDatabaseJooq.getDslContext()
                .selectFrom(USER)
                .fetch();
    }

    @Override
    public boolean update(UserRecord entity) {
        int updatedRecord = UtilsDatabaseJooq.getDslContext()
                .executeUpdate(entity);
        return updatedRecord != 0;
    }

    @Override
    public boolean insert(UserRecord entity) {
        int insertedRecord = UtilsDatabaseJooq.getDslContext()
                .executeInsert(entity);
        return insertedRecord != 0;

    }

    @Override
    public boolean delete(Long aLong) {
        int deletedRecord = UtilsDatabaseJooq.getDslContext()
                .deleteFrom(USER)
                .where(USER.ID_USER.equal(aLong.intValue()))
                .execute();
        return deletedRecord != 0;
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Method will implement later")
    }
}
