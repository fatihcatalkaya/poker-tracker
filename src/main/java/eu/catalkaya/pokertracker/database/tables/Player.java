/*
 * This file is generated by jOOQ.
 */
package eu.catalkaya.pokertracker.database.tables;


import eu.catalkaya.pokertracker.database.DefaultSchema;
import eu.catalkaya.pokertracker.database.Keys;
import eu.catalkaya.pokertracker.database.tables.Transactions.TransactionsPath;
import eu.catalkaya.pokertracker.database.tables.records.PlayerRecord;

import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.InverseForeignKey;
import org.jooq.Name;
import org.jooq.Path;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Player extends TableImpl<PlayerRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PLAYER</code>
     */
    public static final Player PLAYER = new Player();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PlayerRecord> getRecordType() {
        return PlayerRecord.class;
    }

    /**
     * The column <code>PLAYER.ID</code>.
     */
    public final TableField<PlayerRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PLAYER.NAME</code>.
     */
    public final TableField<PlayerRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.CLOB.nullable(false), this, "");

    private Player(Name alias, Table<PlayerRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Player(Name alias, Table<PlayerRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>PLAYER</code> table reference
     */
    public Player(String alias) {
        this(DSL.name(alias), PLAYER);
    }

    /**
     * Create an aliased <code>PLAYER</code> table reference
     */
    public Player(Name alias) {
        this(alias, PLAYER);
    }

    /**
     * Create a <code>PLAYER</code> table reference
     */
    public Player() {
        this(DSL.name("PLAYER"), null);
    }

    public <O extends Record> Player(Table<O> path, ForeignKey<O, PlayerRecord> childPath, InverseForeignKey<O, PlayerRecord> parentPath) {
        super(path, childPath, parentPath, PLAYER);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class PlayerPath extends Player implements Path<PlayerRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> PlayerPath(Table<O> path, ForeignKey<O, PlayerRecord> childPath, InverseForeignKey<O, PlayerRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private PlayerPath(Name alias, Table<PlayerRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public PlayerPath as(String alias) {
            return new PlayerPath(DSL.name(alias), this);
        }

        @Override
        public PlayerPath as(Name alias) {
            return new PlayerPath(alias, this);
        }

        @Override
        public PlayerPath as(Table<?> alias) {
            return new PlayerPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<PlayerRecord, Integer> getIdentity() {
        return (Identity<PlayerRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<PlayerRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_8;
    }

    private transient TransactionsPath _transactions;

    /**
     * Get the implicit to-many join path to the
     * <code>PUBLIC.TRANSACTIONS</code> table
     */
    public TransactionsPath transactions() {
        if (_transactions == null)
            _transactions = new TransactionsPath(this, null, Keys.CONSTRAINT_FE.getInverseKey());

        return _transactions;
    }

    @Override
    public Player as(String alias) {
        return new Player(DSL.name(alias), this);
    }

    @Override
    public Player as(Name alias) {
        return new Player(alias, this);
    }

    @Override
    public Player as(Table<?> alias) {
        return new Player(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Player rename(String name) {
        return new Player(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Player rename(Name name) {
        return new Player(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Player rename(Table<?> name) {
        return new Player(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player where(Condition condition) {
        return new Player(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Player where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Player where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Player where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Player where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Player whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}