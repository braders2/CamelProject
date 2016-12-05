package com.camel.dao.impl;

import com.camel.dao.UserRepository;
import com.camel.tables.tables.records.UserRecord;

import java.util.Collection;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository {
    public Optional<UserRecord> get(Long aLong) {
        return null;
    }

    public Collection<UserRecord> getAll() {
        return null;
    }

    public boolean update(UserRecord entity) {
        return false;
    }

    public boolean insert(UserRecord entity) {
        return false;
    }

    public boolean delete(Long aLong) {
        return false;
    }

    public boolean deleteAll() {
        return false;
    }

    @Override
    public void reportUserLoginFailed() {

    }
}
