package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Orderline;
import com.otto.beekeeperstocksystem.Repositories.OrderlineRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class OrderlineRepositoryImpl extends SQLiteOpenHelper implements OrderlineRepository {
    public static final String TABLE_NAME = "orderline";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "orderlineID";
    public static final String COLUMN_UNITPRICE = "unitPrice";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_ORDER = "order";
    public static final String COLUMN_PRODUCT = "product";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_UNITPRICE + " TEXT NOT NULL , "
            + COLUMN_QUANTITY + " TEXT NOT NULL , "
            + COLUMN_ORDER + " TEXT NOT NULL , "
            + COLUMN_PRODUCT + " TEXT NOT NULL  );";


    public OrderlineRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Orderline findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_UNITPRICE,
                        COLUMN_QUANTITY,
                        COLUMN_ORDER,
                        COLUMN_PRODUCT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Orderline orderline = new Orderline.Builder()
                    .orderlineID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .unitPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_UNITPRICE)))
                    .quantity(cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY)))
                    .build();

            return orderline;
        } else {
            return null;
        }
    }

    @Override
    public Orderline save(Orderline entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getOrderlineID());
        values.put(COLUMN_UNITPRICE, entity.getUnitPrice());
        values.put(COLUMN_QUANTITY, entity.getQuantity());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Orderline insertedEntity = new Orderline.Builder()
                .copy(entity)
                .orderlineID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Orderline update(Orderline entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getOrderlineID());
        values.put(COLUMN_UNITPRICE, entity.getUnitPrice());
        values.put(COLUMN_QUANTITY, entity.getQuantity());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getOrderlineID())}
        );
        return entity;
    }

    @Override
    public Orderline delete(Orderline entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getOrderlineID())});
        return entity;
    }

    @Override
    public Set<Orderline> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Orderline> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Orderline orderline = new Orderline.Builder()
                        .orderlineID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .unitPrice(cursor.getDouble(cursor.getColumnIndex(COLUMN_UNITPRICE)))
                        .quantity(cursor.getDouble(cursor.getColumnIndex(COLUMN_QUANTITY)))
                        .build();
                candidates.add(orderline);
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

