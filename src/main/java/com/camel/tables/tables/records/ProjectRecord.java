/**
 * This class is generated by jOOQ
 */
package com.camel.tables.tables.records;


import com.camel.tables.tables.Project;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ProjectRecord extends UpdatableRecordImpl<ProjectRecord> implements Record5<Integer, String, Date, Date, Integer> {

    private static final long serialVersionUID = 16387949;

    /**
     * Setter for <code>database.project.id_project</code>.
     */
    public void setIdProject(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>database.project.id_project</code>.
     */
    public Integer getIdProject() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>database.project.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>database.project.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>database.project.time_from</code>.
     */
    public void setTimeFrom(Date value) {
        set(2, value);
    }

    /**
     * Getter for <code>database.project.time_from</code>.
     */
    public Date getTimeFrom() {
        return (Date) get(2);
    }

    /**
     * Setter for <code>database.project.time_to</code>.
     */
    public void setTimeTo(Date value) {
        set(3, value);
    }

    /**
     * Getter for <code>database.project.time_to</code>.
     */
    public Date getTimeTo() {
        return (Date) get(3);
    }

    /**
     * Setter for <code>database.project.id_customer</code>.
     */
    public void setIdCustomer(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>database.project.id_customer</code>.
     */
    public Integer getIdCustomer() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Date, Date, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Date, Date, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Project.PROJECT.ID_PROJECT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Project.PROJECT.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field3() {
        return Project.PROJECT.TIME_FROM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field4() {
        return Project.PROJECT.TIME_TO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Project.PROJECT.ID_CUSTOMER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getIdProject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value3() {
        return getTimeFrom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value4() {
        return getTimeTo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getIdCustomer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord value1(Integer value) {
        setIdProject(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord value3(Date value) {
        setTimeFrom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord value4(Date value) {
        setTimeTo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord value5(Integer value) {
        setIdCustomer(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProjectRecord values(Integer value1, String value2, Date value3, Date value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProjectRecord
     */
    public ProjectRecord() {
        super(Project.PROJECT);
    }

    /**
     * Create a detached, initialised ProjectRecord
     */
    public ProjectRecord(Integer idProject, String name, Date timeFrom, Date timeTo, Integer idCustomer) {
        super(Project.PROJECT);

        set(0, idProject);
        set(1, name);
        set(2, timeFrom);
        set(3, timeTo);
        set(4, idCustomer);
    }
}
