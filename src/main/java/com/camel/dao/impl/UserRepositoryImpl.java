package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.interfaces.functional.FunctionalInterfaceDao;
import com.camel.tables.tables.records.UserRecord;
import com.camel.utils.UtilsDatabaseJooq;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.User.USER;

public class UserRepositoryImpl implements UserRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    private DSLContext dslContext;

    public UserRepositoryImpl() {
        dslContext = UtilsDatabaseJooq.getDslContext();
    }

    @Override
    public Optional<UserRecord> get(Long aLong) {
        try {
            FunctionalInterfaceDao<Optional<UserRecord>> functionalInterfaceDao = () -> dslContext.selectFrom(USER)
                    .where(USER.ID_USER.equal(aLong.intValue()))
                    .fetchOptional();
            return functionalInterfaceDao.execute(dslContext);
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public Collection<UserRecord> getAll() {
        try {
            FunctionalInterfaceDao<Collection<UserRecord>> functionalInterfaceDao = () -> dslContext.selectFrom(USER)
                                                                                .fetch();
            return functionalInterfaceDao.execute(dslContext);
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public boolean update(UserRecord entity) {
        try {
            int updatedRecord = dslContext.executeUpdate(entity);
            dslContext.close();
            return updatedRecord != 0;
        } catch (DataAccessException exception) {
            LOGGER.error("error get data", exception);
            throw new DataAccessException("error get data", exception);
        }
    }

    @Override
    public boolean insert(UserRecord entity) {
        try {
            int insertedRecord = dslContext.executeInsert(entity);
            dslContext.close();
            return insertedRecord != 0;
        } catch (DataAccessException exception) {
            LOGGER.error("error insert data", exception);
            throw new DataAccessException("error insert data", exception);
        }
    }

    @Override
    public boolean delete(Long aLong) {
        try {
            int deletedRecord = dslContext.deleteFrom(USER)
                    .where(USER.ID_USER.equal(aLong.intValue()))
                    .execute();
            dslContext.close();
            return deletedRecord != 0;
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
