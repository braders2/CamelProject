package com.camel.dao.impl;

import com.camel.dao.UserProjectsRepository;
import com.camel.dto.UserProjectsDTO;
import com.camel.tables.tables.Project;
import com.camel.tables.tables.UserProject;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.tables.tables.records.UserProjectRecord;
import com.camel.utils.UtilsDatabaseJooq;
import com.google.gson.Gson;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.camel.tables.tables.Project.PROJECT;
import static com.camel.tables.tables.UserProject.*;

public class UserProjectsRepositoryImpl implements UserProjectsRepository {

    private DSLContext dslContext;

    public UserProjectsRepositoryImpl() {
        this.dslContext = UtilsDatabaseJooq.getDslContext();
    }

    @Override
    public Optional<UserProjectRecord> get(Long aLong) {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public Collection<UserProjectRecord> getAll() {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public boolean update(UserProjectRecord entity) {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public boolean insert(UserProjectRecord entity) {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public boolean delete(Long aLong) {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Method will be implement later");
    }

    @Override
    public Collection<ProjectRecord> getAllUserProjects(Long id) {
        Collection<ProjectRecord> projectRecords = dslContext.selectFrom(PROJECT.join(USER_PROJECT)
                                                             .on(USER_PROJECT.PROJECT_ID_PROJECT.equal(PROJECT.ID_PROJECT)))
                                                             .where(USER_PROJECT.USERS_ID_USER.equal(id.intValue()))
                                                             .fetchInto(PROJECT);
        dslContext.close();
        return projectRecords;
    }
}
