package com.camel.process;

import com.camel.utils.UtilsDatabaseJooq;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import static com.camel.tables.tables.User.USER;

/**
 * Created by Mateusz Dobrowolski on 29.11.2016.
 */
public class SetUserFromDatabaseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        DSLContext create = DSL.using(UtilsDatabaseJooq.connection, SQLDialect.MYSQL);
        Result<Record> resultList = create.select().from(USER).fetch();

    }
}
