/*
 * This file is generated by jOOQ.
 */
package eu.catalkaya.pokertracker.database.tables.records;


import eu.catalkaya.pokertracker.database.tables.Login;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class LoginRecord extends UpdatableRecordImpl<LoginRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>LOGIN.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>LOGIN.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>LOGIN.USERNAME</code>.
     */
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>LOGIN.USERNAME</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>LOGIN.PASSWORD</code>.
     */
    public void setPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>LOGIN.PASSWORD</code>.
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>LOGIN.ROLE</code>.
     */
    public void setRole(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>LOGIN.ROLE</code>.
     */
    public String getRole() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached LoginRecord
     */
    public LoginRecord() {
        super(Login.LOGIN);
    }

    /**
     * Create a detached, initialised LoginRecord
     */
    public LoginRecord(Integer id, String username, String password, String role) {
        super(Login.LOGIN);

        setId(id);
        setUsername(username);
        setPassword(password);
        setRole(role);
        resetChangedOnNotNull();
    }
}