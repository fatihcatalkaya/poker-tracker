/*
 * This file is generated by jOOQ.
 */
package eu.catalkaya.pokertracker.database.tables;


import eu.catalkaya.pokertracker.database.DefaultSchema;
import eu.catalkaya.pokertracker.database.Keys;
import eu.catalkaya.pokertracker.database.tables.Player.PlayerPath;
import eu.catalkaya.pokertracker.database.tables.records.TransactionsRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
public class Transactions extends TableImpl<TransactionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>TRANSACTIONS</code>
     */
    public static final Transactions TRANSACTIONS = new Transactions();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TransactionsRecord> getRecordType() {
        return TransactionsRecord.class;
    }

    /**
     * The column <code>TRANSACTIONS.ID</code>.
     */
    public final TableField<TransactionsRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>TRANSACTIONS.PLAYER_ID</code>.
     */
    public final TableField<TransactionsRecord, Integer> PLAYER_ID = createField(DSL.name("PLAYER_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>TRANSACTIONS.AMOUNT</code>.
     */
    public final TableField<TransactionsRecord, Float> AMOUNT = createField(DSL.name("AMOUNT"), SQLDataType.REAL.nullable(false), this, "");

    /**
     * The column <code>TRANSACTIONS.CREATED_AT</code>.
     */
    public final TableField<TransactionsRecord, LocalDateTime> CREATED_AT = createField(DSL.name("CREATED_AT"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field(DSL.raw("CURRENT_TIMESTAMP"), SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>TRANSACTIONS.COMMENT</code>.
     */
    public final TableField<TransactionsRecord, String> COMMENT = createField(DSL.name("COMMENT"), SQLDataType.CLOB.nullable(false).defaultValue(DSL.field(DSL.raw("''"), SQLDataType.CLOB)), this, "");

    private Transactions(Name alias, Table<TransactionsRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private Transactions(Name alias, Table<TransactionsRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>TRANSACTIONS</code> table reference
     */
    public Transactions(String alias) {
        this(DSL.name(alias), TRANSACTIONS);
    }

    /**
     * Create an aliased <code>TRANSACTIONS</code> table reference
     */
    public Transactions(Name alias) {
        this(alias, TRANSACTIONS);
    }

    /**
     * Create a <code>TRANSACTIONS</code> table reference
     */
    public Transactions() {
        this(DSL.name("TRANSACTIONS"), null);
    }

    public <O extends Record> Transactions(Table<O> path, ForeignKey<O, TransactionsRecord> childPath, InverseForeignKey<O, TransactionsRecord> parentPath) {
        super(path, childPath, parentPath, TRANSACTIONS);
    }

    /**
     * A subtype implementing {@link Path} for simplified path-based joins.
     */
    public static class TransactionsPath extends Transactions implements Path<TransactionsRecord> {

        private static final long serialVersionUID = 1L;
        public <O extends Record> TransactionsPath(Table<O> path, ForeignKey<O, TransactionsRecord> childPath, InverseForeignKey<O, TransactionsRecord> parentPath) {
            super(path, childPath, parentPath);
        }
        private TransactionsPath(Name alias, Table<TransactionsRecord> aliased) {
            super(alias, aliased);
        }

        @Override
        public TransactionsPath as(String alias) {
            return new TransactionsPath(DSL.name(alias), this);
        }

        @Override
        public TransactionsPath as(Name alias) {
            return new TransactionsPath(alias, this);
        }

        @Override
        public TransactionsPath as(Table<?> alias) {
            return new TransactionsPath(alias.getQualifiedName(), this);
        }
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public Identity<TransactionsRecord, Integer> getIdentity() {
        return (Identity<TransactionsRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<TransactionsRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_F;
    }

    @Override
    public List<ForeignKey<TransactionsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_FE);
    }

    private transient PlayerPath _player;

    /**
     * Get the implicit join path to the <code>PUBLIC.PLAYER</code> table.
     */
    public PlayerPath player() {
        if (_player == null)
            _player = new PlayerPath(this, Keys.CONSTRAINT_FE, null);

        return _player;
    }

    @Override
    public Transactions as(String alias) {
        return new Transactions(DSL.name(alias), this);
    }

    @Override
    public Transactions as(Name alias) {
        return new Transactions(alias, this);
    }

    @Override
    public Transactions as(Table<?> alias) {
        return new Transactions(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(String name) {
        return new Transactions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(Name name) {
        return new Transactions(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Transactions rename(Table<?> name) {
        return new Transactions(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Condition condition) {
        return new Transactions(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public Transactions where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public Transactions whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }
}