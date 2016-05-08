package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;


import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Customer;
import com.otto.beekeeperstocksystem.Repositories.CustomerRepository;

/**
 * Created by Quam on 4/22/2016.
 */
public class CustomerRepositoryImpl extends SQLiteOpenHelper implements CustomerRepository {
    public static final String TABLE_NAME = "customer";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "customerID";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PERSON = "person";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_ADDRESS + " TEXT NOT NULL );";


    public CustomerRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Customer findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_ADDRESS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Customer customer = new Customer.Builder‭()
                    .customerID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                    .build();

            return customer;
        } else {
            return null;
        }
    }

    @Override
    public Customer save(Customer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCustomerID());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Customer insertedEntity = new Customer.Builder‭()
                .copy(entity)
                .customerID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCustomerID());
        values.put(COLUMN_ADDRESS, entity.getAddress());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCustomerID())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCustomerID())});
        return entity;
    }

    @Override
    public Set<Customer> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Customer customer = new Customer.Builder‭()
                        .customerID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                        .build();
                candidates.add(customer);
            } while (cursor.moveToNext());
        }
        return candidates;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
