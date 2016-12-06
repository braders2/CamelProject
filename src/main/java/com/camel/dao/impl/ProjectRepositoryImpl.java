package com.camel.dao.impl;

import com.camel.dao.ProjectRepository;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.utils.UtilsDatabaseJooq;
import org.jooq.DSLContext;

import java.util.Collection;
import java.util.Optional;

import static com.camel.tables.tables.Project.PROJECT;

public class ProjectRepositoryImpl implements ProjectRepository {

    private DSLContext dslContext;

    public ProjectRepositoryImpl() {
        this.dslContext = UtilsDatabaseJooq.getDslContext();
    }

    @Override
    public Optional<ProjectRecord> get(Long aLong) {
        Optional<ProjectRecord> projectRecord = dslContext.selectFrom(PROJECT)
                                                          .where(PROJECT.ID_PROJECT.equal(aLong.intValue()))
                                                          .fetchOptional();
        dslContext.close();
        return projectRecord;
    }

    @Override
    public Collection<ProjectRecord> getAll() {
        Collection<ProjectRecord> projectRecordCollection = dslContext.selectFrom(PROJECT)
                                                                      .fetch();
        dslContext.close();
        return  projectRecordCollection;
    }

    @Override
    public boolean update(ProjectRecord entity) {
        int updatedRecord = dslContext.executeUpdate(entity);
        dslContext.close();
        return updatedRecord != 0;
    }

    @Override
    public boolean insert(ProjectRecord entity) {
        int insertedRecord = dslContext.executeInsert(entity);
        dslContext.close();
        return insertedRecord != 0;
    }

    @Override
    public boolean delete(Long aLong) {
        int deletedRecord = dslContext.deleteFrom(PROJECT)
                                      .where(PROJECT.ID_PROJECT.equal(aLong.intValue()))
                                      .execute();
        dslContext.close();
        return deletedRecord != 0;
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("Method will implement later");
    }
}
