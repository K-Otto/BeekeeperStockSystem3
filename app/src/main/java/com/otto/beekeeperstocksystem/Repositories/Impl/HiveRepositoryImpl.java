package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Hive;
import com.otto.beekeeperstocksystem.Repositories.HiveRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class HiveRepositoryImpl extends SQLiteOpenHelper implements HiveRepository {
    public static final String TABLE_NAME = "hive";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "hiveID";
    public static final String COLUMN_HIVESTATE = "hiveState";
    public static final String COLUMN_SUBLOCATION = "subLocation";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_HIVESTATE + " TEXT NOT NULL , "
            + COLUMN_SUBLOCATION + " TEXT NOT NULL  );";


    public HiveRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Hive findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_HIVESTATE,
                        COLUMN_SUBLOCATION},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Hive hive = new Hive.Builder()
                    .hiveID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .hiveState(cursor.getString(cursor.getColumnIndex(COLUMN_HIVESTATE)))
                    .build();

            return hive;
        } else {
            return null;
        }
    }

    @Override
    public Hive save(Hive entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHiveID());
        values.put(COLUMN_HIVESTATE, entity.getHiveState());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Hive insertedEntity = new Hive.Builder()
                .copy(entity)
                .hiveID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Hive update(Hive entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHiveID());
        values.put(COLUMN_HIVESTATE, entity.getHiveState());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHiveID())}
        );
        return entity;
    }

    @Override
    public Hive delete(Hive entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHiveID())});
        return entity;
    }

    @Override
    public Set<Hive> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Hive> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Hive hive = new Hive.Builder()
                        .hiveID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .hiveState(cursor.getString(cursor.getColumnIndex(COLUMN_HIVESTATE)))
                        .build();
                candidates.add(hive);
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
