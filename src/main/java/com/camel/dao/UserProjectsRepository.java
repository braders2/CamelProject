package com.camel.dao;

import com.camel.tables.tables.records.ProjectRecord;
import com.camel.tables.tables.records.UserProjectRecord;

import java.util.Collection;

public interface UserProjectsRepository extends GenericRepository<Long, UserProjectRecord> {

    Collection<ProjectRecord> getAllUserProjects(Long id);
}
