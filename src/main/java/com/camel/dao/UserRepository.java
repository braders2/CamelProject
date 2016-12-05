package com.camel.dao;

import com.camel.tables.tables.records.UserRecord;

public interface UserRepository extends GenericRepository <Long, UserRecord> {
    void reportUserLoginFailed();
}
