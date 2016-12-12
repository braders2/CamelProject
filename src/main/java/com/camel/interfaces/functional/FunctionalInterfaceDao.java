package com.camel.interfaces.functional;

import org.jooq.DSLContext;

@FunctionalInterface
public interface FunctionalInterfaceDao<T> {
    T executeSQL();

    default T execute(DSLContext dslContext) {
        T result =  executeSQL();
        dslContext.close();
        return result;
    }
}
