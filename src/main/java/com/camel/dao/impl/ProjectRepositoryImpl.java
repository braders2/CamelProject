package com.camel.dao.impl;


import com.camel.dao.ProjectRepository;
import com.camel.tables.tables.records.ProjectRecord;

import java.util.Collection;
import java.util.Optional;

public class ProjectRepositoryImpl implements ProjectRepository {
    public Optional<ProjectRecord> get(Long aLong) {
        return null;
    }

    public Collection<ProjectRecord> getAll() {
        return null;
    }

    public boolean update(ProjectRecord entity) {
        return false;
    }

    public boolean insert(ProjectRecord entity) {
        return false;
    }

    public boolean delete(Long aLong) {
        return false;
    }

    public boolean deleteAll() {
        return false;
    }
}
