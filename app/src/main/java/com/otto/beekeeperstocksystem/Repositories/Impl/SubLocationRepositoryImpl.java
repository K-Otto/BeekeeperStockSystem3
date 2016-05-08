package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.SubLocation;
import com.otto.beekeeperstocksystem.Repositories.SubLocationRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class SubLocationRepositoryImpl extends SQLiteOpenHelper implements SubLocationRepository {
    public static final String TABLE_NAME = "subLocation";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "subLocationID";
    public static final String COLUMN_SUBLOCATIONNAME = "subLocationName";
    public static final String COLUMN_BEEKEEPER = "beekeeper";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_SUBLOCATIONNAME + " TEXT NOT NULL , "
            + COLUMN_BEEKEEPER + " TEXT NOT NULL  );";


    public SubLocationRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public SubLocation findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_SUBLOCATIONNAME,
                        COLUMN_BEEKEEPER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final SubLocation subLocation = new SubLocation.Builder()
                    .subLocID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .subLocationName(cursor.getString(cursor.getColumnIndex(COLUMN_SUBLOCATIONNAME)))
                    .build();

            return subLocation;
        } else {
            return null;
        }
    }

    @Override
    public SubLocation save(SubLocation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSubLocID());
        values.put(COLUMN_SUBLOCATIONNAME, entity.getSubLocationName());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        SubLocation insertedEntity = new SubLocation.Builder()
                .copy(entity)
                .subLocID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public SubLocation update(SubLocation entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getSubLocID());
        values.put(COLUMN_SUBLOCATIONNAME, entity.getSubLocationName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSubLocID())}
        );
        return entity;
    }

    @Override
    public SubLocation delete(SubLocation entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getSubLocID())});
        return entity;
    }

    @Override
    public Set<SubLocation> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<SubLocation> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final SubLocation subLocation = new SubLocation.Builder()
                        .subLocID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .subLocationName(cursor.getString(cursor.getColumnIndex(COLUMN_SUBLOCATIONNAME)))
                        .build();
                candidates.add(subLocation);
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
