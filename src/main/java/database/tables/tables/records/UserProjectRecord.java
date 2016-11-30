/**
 * This class is generated by jOOQ
 */
package com.camel.tables.tables.records;


import com.camel.tables.tables.UserProject;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class UserProjectRecord extends UpdatableRecordImpl<UserProjectRecord> implements Record4<Integer, Integer, Date, Date> {

    private static final long serialVersionUID = 1527691246;

    /**
     * Setter for <code>database.user_project.projects_id_project</code>.
     */
    public void setProjectsIdProject(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>database.user_project.projects_id_project</code>.
     */
    public Integer getProjectsIdProject() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>database.user_project.users_id_user</code>.
     */
    public void setUsersIdUser(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>database.user_project.users_id_user</code>.
     */
    public Integer getUsersIdUser() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>database.user_project.date_from</code>.
     */
    public void setDateFrom(Date value) {
        set(2, value);
    }

    /**
     * Getter for <code>database.user_project.date_from</code>.
     */
    public Date getDateFrom() {
        return (Date) get(2);
    }

    /**
     * Setter for <code>database.user_project.date_to</code>.
     */
    public void setDateTo(Date value) {
        set(3, value);
    }

    /**
     * Getter for <code>database.user_project.date_to</code>.
     */
    public Date getDateTo() {
        return (Date) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Date, Date> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, Integer, Date, Date> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return UserProject.USER_PROJECT.PROJECTS_ID_PROJECT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return UserProject.USER_PROJECT.USERS_ID_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field3() {
        return UserProject.USER_PROJECT.DATE_FROM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field4() {
        return UserProject.USER_PROJECT.DATE_TO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getProjectsIdProject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getUsersIdUser();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value3() {
        return getDateFrom();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value4() {
        return getDateTo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProjectRecord value1(Integer value) {
        setProjectsIdProject(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProjectRecord value2(Integer value) {
        setUsersIdUser(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProjectRecord value3(Date value) {
        setDateFrom(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProjectRecord value4(Date value) {
        setDateTo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserProjectRecord values(Integer value1, Integer value2, Date value3, Date value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserProjectRecord
     */
    public UserProjectRecord() {
        super(UserProject.USER_PROJECT);
    }

    /**
     * Create a detached, initialised UserProjectRecord
     */
    public UserProjectRecord(Integer projectsIdProject, Integer usersIdUser, Date dateFrom, Date dateTo) {
        super(UserProject.USER_PROJECT);

        set(0, projectsIdProject);
        set(1, usersIdUser);
        set(2, dateFrom);
        set(3, dateTo);
    }
}