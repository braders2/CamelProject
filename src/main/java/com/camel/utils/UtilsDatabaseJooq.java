package com.camel.utils;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilsDatabaseJooq {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/database";

    private static Connection connection;
    private static DSLContext dslContext;

    public static DSLContext getDslContext() {
        if (dslContext == null) {
            dslContext = DSL.using(UtilsDatabaseJooq.connection, SQLDialect.MYSQL);
        }
        return dslContext;
    }

    public static void initialzeConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
