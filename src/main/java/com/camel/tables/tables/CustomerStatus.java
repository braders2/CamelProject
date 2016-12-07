/**
 * This class is generated by jOOQ
 */
package com.camel.tables.tables;


import com.camel.tables.Database;
import com.camel.tables.Keys;
import com.camel.tables.tables.records.CustomerStatusRecord;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CustomerStatus extends TableImpl<CustomerStatusRecord> {

    private static final long serialVersionUID = 1873980572;

    /**
     * The reference instance of <code>database.customer_status</code>
     */
    public static final CustomerStatus CUSTOMER_STATUS = new CustomerStatus();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CustomerStatusRecord> getRecordType() {
        return CustomerStatusRecord.class;
    }

    /**
     * The column <code>database.customer_status.id</code>.
     */
    public final TableField<CustomerStatusRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>database.customer_status.name</code>.
     */
    public final TableField<CustomerStatusRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(10).nullable(false), this, "");

    /**
     * The column <code>database.customer_status.sort_order</code>.
     */
    public final TableField<CustomerStatusRecord, Integer> SORT_ORDER = createField("sort_order", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>database.customer_status</code> table reference
     */
    public CustomerStatus() {
        this("customer_status", null);
    }

    /**
     * Create an aliased <code>database.customer_status</code> table reference
     */
    public CustomerStatus(String alias) {
        this(alias, CUSTOMER_STATUS);
    }

    private CustomerStatus(String alias, Table<CustomerStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private CustomerStatus(String alias, Table<CustomerStatusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Database.DATABASE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CustomerStatusRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CUSTOMER_STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CustomerStatusRecord> getPrimaryKey() {
        return Keys.KEY_CUSTOMER_STATUS_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CustomerStatusRecord>> getKeys() {
        return Arrays.<UniqueKey<CustomerStatusRecord>>asList(Keys.KEY_CUSTOMER_STATUS_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerStatus as(String alias) {
        return new CustomerStatus(alias, this);
    }

    /**
     * Rename this table
     */
    public CustomerStatus rename(String name) {
        return new CustomerStatus(name, null);
    }
}