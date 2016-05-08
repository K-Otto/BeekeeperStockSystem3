package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Order;
import com.otto.beekeeperstocksystem.Repositories.OrderRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class OrderRepositoryImpl extends SQLiteOpenHelper implements OrderRepository {
    public static final String TABLE_NAME = "order";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "orderID";
    public static final String COLUMN_ORDERDATE = "orderDate";
    public static final String COLUMN_SALESMAN = "salesman";
    public static final String COLUMN_CUSTOMER = "customer";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_ORDERDATE + " TEXT NOT NULL , "
            + COLUMN_SALESMAN + " TEXT NOT NULL , "
            + COLUMN_CUSTOMER + " TEXT NOT NULL  );";


    public OrderRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Order findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_ORDERDATE,
                        COLUMN_SALESMAN,
                        COLUMN_CUSTOMER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Order order = new Order.Builder()
                    .orderID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .orderDate(cursor.getString(cursor.getColumnIndex(COLUMN_ORDERDATE)))
                    .build();

            return order;
        } else {
            return null;
        }
    }

    @Override
    public Order save(Order entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getOrderID());
        values.put(COLUMN_ORDERDATE, entity.getSalesDate());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Order insertedEntity = new Order.Builder()
                .copy(entity)
                .orderID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Order update(Order entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getOrderID());
        values.put(COLUMN_ORDERDATE, entity.getSalesDate());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getOrderID())}
        );
        return entity;
    }

    @Override
    public Order delete(Order entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getOrderID())});
        return entity;
    }

    @Override
    public Set<Order> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Order> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Order order = new Order.Builder()
                        .orderID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .orderDate(cursor.getString(cursor.getColumnIndex(COLUMN_ORDERDATE)))

                        .build();
                candidates.add(order);
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


