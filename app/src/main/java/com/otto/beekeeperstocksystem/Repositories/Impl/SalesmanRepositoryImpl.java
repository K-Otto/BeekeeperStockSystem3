package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Salesman;
import com.otto.beekeeperstocksystem.Repositories.SalesmanRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class SalesmanRepositoryImpl extends SQLiteOpenHelper implements SalesmanRepository {
    public static final String TABLE_NAME = "salesman";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "salesmanID";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_PERSON = "person";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_SALARY + " TEXT NOT NULL , "
            + COLUMN_PERSON + " TEXT NOT NULL  );";


    public SalesmanRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Salesman findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_SALARY,
                        COLUMN_PERSON},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Salesman salesman = new Salesman.Builder‭()
                    .salesmanID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                    .build();

            return salesman;
        } else {
            return null;
        }
    }

    @Override
    public Salesman save(Salesman entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSalesmanID());
        values.put(COLUMN_SALARY, entity.getSalary‭‭());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Salesman insertedEntity = new Salesman.Builder‭()
                .copy(entity)
                .salesmanID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Salesman update(Salesman entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSalesmanID());
        values.put(COLUMN_SALARY, entity.getSalary‭‭());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSalesmanID())}
        );
        return entity;
    }

    @Override
    public Salesman delete(Salesman entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSalesmanID())});
        return entity;
    }

    @Override
    public Set<Salesman> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Salesman> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Salesman salesman = new Salesman.Builder‭()
                        .salesmanID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                        .build();
                candidates.add(salesman);
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

