/**
 * This class is generated by jOOQ
 */
package com.camel.tables;


import com.camel.tables.tables.Project;
import com.camel.tables.tables.User;
import com.camel.tables.tables.UserProject;
import com.camel.tables.tables.records.ProjectRecord;
import com.camel.tables.tables.records.UserProjectRecord;
import com.camel.tables.tables.records.UserRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>database</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ProjectRecord, Integer> IDENTITY_PROJECT = Identities0.IDENTITY_PROJECT;
    public static final Identity<UserRecord, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = UniqueKeys0.KEY_PROJECT_PRIMARY;
    public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserProjectRecord> KEY_USER_PROJECT_PRIMARY = UniqueKeys0.KEY_USER_PROJECT_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<UserProjectRecord, ProjectRecord> FK_PROJECTS_HAS_USERS_PROJECTS = ForeignKeys0.FK_PROJECTS_HAS_USERS_PROJECTS;
    public static final ForeignKey<UserProjectRecord, UserRecord> FK_PROJECTS_HAS_USERS_USERS1 = ForeignKeys0.FK_PROJECTS_HAS_USERS_USERS1;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<ProjectRecord, Integer> IDENTITY_PROJECT = createIdentity(Project.PROJECT, Project.PROJECT.ID_PROJECT);
        public static Identity<UserRecord, Integer> IDENTITY_USER = createIdentity(User.USER, User.USER.ID_USER);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ProjectRecord> KEY_PROJECT_PRIMARY = createUniqueKey(Project.PROJECT, "KEY_project_PRIMARY", Project.PROJECT.ID_PROJECT);
        public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = createUniqueKey(User.USER, "KEY_user_PRIMARY", User.USER.ID_USER);
        public static final UniqueKey<UserProjectRecord> KEY_USER_PROJECT_PRIMARY = createUniqueKey(UserProject.USER_PROJECT, "KEY_user_project_PRIMARY", UserProject.USER_PROJECT.PROJECTS_ID_PROJECT, UserProject.USER_PROJECT.USERS_ID_USER);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<UserProjectRecord, ProjectRecord> FK_PROJECTS_HAS_USERS_PROJECTS = createForeignKey(com.camel.tables.Keys.KEY_PROJECT_PRIMARY, UserProject.USER_PROJECT, "fk_projects_has_users_projects", UserProject.USER_PROJECT.PROJECTS_ID_PROJECT);
        public static final ForeignKey<UserProjectRecord, UserRecord> FK_PROJECTS_HAS_USERS_USERS1 = createForeignKey(com.camel.tables.Keys.KEY_USER_PRIMARY, UserProject.USER_PROJECT, "fk_projects_has_users_users1", UserProject.USER_PROJECT.USERS_ID_USER);
    }
}
