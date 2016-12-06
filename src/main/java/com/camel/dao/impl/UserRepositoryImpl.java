package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.UserDao;
import com.camel.utils.UtilsDatabaseJooq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.User.USER;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

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
        int updatedRecords = UtilsDatabaseJooq.getDslContext()
                                              .executeUpdate(entity);
        return updatedRecords != 0;
    }

    @Override
    public boolean insert(UserRecord entity) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
