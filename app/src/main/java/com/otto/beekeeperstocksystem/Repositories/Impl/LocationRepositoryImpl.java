package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Location;
import com.otto.beekeeperstocksystem.Repositories.LocationRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class LocationRepositoryImpl extends SQLiteOpenHelper implements LocationRepository {
    public static final String TABLE_NAME = "locations";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "locationID";
    public static final String COLUMN_LOCATIONNAME = "locationName";
    public static final String COLUMN_BEEKEEPER = "beekeeper";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_LOCATIONNAME + " TEXT NOT NULL , "
            + COLUMN_BEEKEEPER + " TEXT NOT NULL  );";


    public LocationRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Location findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_LOCATIONNAME,
                        COLUMN_BEEKEEPER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Location locations = new Location.Builder‭()
                    .LocID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .locationName‭(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATIONNAME)))
                    .build();

            return locations;
        } else {
            return null;
        }
    }

    @Override
    public Location save(Location entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLocID());
        values.put(COLUMN_LOCATIONNAME, entity.getLocationName‭‭());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Location insertedEntity = new Location.Builder‭()
                .copy(entity)
                .LocID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Location update(Location entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getLocID());
        values.put(COLUMN_LOCATIONNAME, entity.getLocationName‭‭());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLocID())}
        );
        return entity;
    }

    @Override
    public Location delete(Location entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getLocID())});
        return entity;
    }

    @Override
    public Set<Location> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Location> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Location locations = new Location.Builder‭()
                        .LocID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .locationName‭(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATIONNAME)))
                        .build();
                candidates.add(locations);
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
