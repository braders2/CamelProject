package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.UtilsDatabaseJooq;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;

import static com.camel.tables.tables.User.USER;

public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<UserRecord> get(Long aLong) {
        try {
            Function<DSLContext, Optional<UserRecord>> function = (dslContext) -> dslContext.selectFrom(USER)
                    .where(USER.ID_USER.equal(aLong.intValue()))
                    .fetchOptional();
            return UtilsDatabaseJooq.executeQuery(function);
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public Collection<UserRecord> getAll() {
        try {
            Function<DSLContext, Collection<UserRecord>> function = (dslContext) -> dslContext.selectFrom(USER)
                    .fetch();
            return UtilsDatabaseJooq.executeQuery(function);
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public boolean update(UserRecord entity) {
        try {
            Function<DSLContext, Integer> function = (dslContext) -> dslContext.executeUpdate(entity);
            return UtilsDatabaseJooq.executeQuery(function) != 0;
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public boolean insert(UserRecord entity) {
        try {
            Function<DSLContext, Integer> function = (dslContext) -> dslContext.executeInsert(entity);
            return UtilsDatabaseJooq.executeQuery(function) != 0;
        } catch (DataAccessException exception) {
            LOGGER.error("error insert data", exception);
            throw new DataAccessException("error insert data", exception);
        }
    }

    @Override
    public boolean delete(Long aLong) {
        try {
            Function<DSLContext, Integer> function = (dslContext) -> dslContext.deleteFrom(USER)
                                                                                .where(USER.ID_USER.equal(aLong.intValue()))
                                                                                .execute();
            return UtilsDatabaseJooq.executeQuery(function) != 0;
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Method will be implement later");
    }
}
