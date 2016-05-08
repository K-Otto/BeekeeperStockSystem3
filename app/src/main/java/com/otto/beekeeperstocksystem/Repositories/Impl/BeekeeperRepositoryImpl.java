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
import com.otto.beekeeperstocksystem.Domain.Beekeeper;
import com.otto.beekeeperstocksystem.Repositories.BeekeeperRepository;

/**
 * Created by Quam on 4/22/2016.
 */
public class BeekeeperRepositoryImpl extends SQLiteOpenHelper implements BeekeeperRepository{
    public static final String TABLE_NAME = "beekeepers";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "beekeperID";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_PERSON = "person";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_SALARY + " TEXT NOT NULL );";


    public BeekeeperRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Beekeeper findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_SALARY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Beekeeper beekeeper = new Beekeeper.Builder‭()
                    .beekeeperID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                    .build();

            return beekeeper;
        } else {
            return null;
        }
    }

    @Override
    public Beekeeper save(Beekeeper entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getBeekeeperID());
        values.put(COLUMN_SALARY, entity.getSalary‭‭());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Beekeeper insertedEntity = new Beekeeper.Builder‭()
                .copy(entity)
                .beekeeperID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Beekeeper update(Beekeeper entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getBeekeeperID());
        values.put(COLUMN_SALARY, entity.getSalary‭‭());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getBeekeeperID())}
        );
        return entity;
    }

    @Override
    public Beekeeper delete(Beekeeper entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getBeekeeperID())});
        return entity;
    }

    @Override
    public Set<Beekeeper> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Beekeeper> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Beekeeper beekeeper = new Beekeeper.Builder‭()
                        .beekeeperID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .salary(cursor.getDouble(cursor.getColumnIndex(COLUMN_SALARY)))
                        .build();
                candidates.add(beekeeper);
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
