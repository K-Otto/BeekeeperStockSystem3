package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Supers;
import com.otto.beekeeperstocksystem.Repositories.SuperRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class SuperRepositoryImpl extends SQLiteOpenHelper implements SuperRepository {
    public static final String TABLE_NAME = "supers";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "superID";
    public static final String COLUMN_SUPERSTATE = "superState";
    public static final String COLUMN_HIVE = "hive";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_SUPERSTATE + " TEXT NOT NULL , "
            + COLUMN_HIVE + " TEXT NOT NULL  );";


    public SuperRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Supers findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_SUPERSTATE,
                        COLUMN_HIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Supers supers = new Supers.Builder()
                    .superID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .superState(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERSTATE)))
                    .build();

            return supers;
        } else {
            return null;
        }
    }

    @Override
    public Supers save(Supers entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSuperID());
        values.put(COLUMN_SUPERSTATE, entity.getSuperState());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Supers insertedEntity = new Supers.Builder()
                .copy(entity)
                .superID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Supers update(Supers entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSuperID());
        values.put(COLUMN_SUPERSTATE, entity.getSuperState());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSuperID())}
        );
        return entity;
    }

    @Override
    public Supers delete(Supers entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSuperID())});
        return entity;
    }

    @Override
    public Set<Supers> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Supers> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Supers supers = new Supers.Builder()
                        .superID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .superState(cursor.getString(cursor.getColumnIndex(COLUMN_SUPERSTATE)))
                        .build();
                candidates.add(supers);
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

