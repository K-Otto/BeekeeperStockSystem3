package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Harvest;
import com.otto.beekeeperstocksystem.Repositories.HarvestRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class HarvestRepositoryImpl extends SQLiteOpenHelper implements HarvestRepository {
    public static final String TABLE_NAME = "harvest";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "harvestID";
    public static final String COLUMN_HARVESTDATE = "harvestDate";
    public static final String COLUMN_TOTALWEIGHT = "totalWeight";
    public static final String COLUMN_SUBLOCATION = "subLocation";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_HARVESTDATE + " TEXT NOT NULL , "
            + COLUMN_TOTALWEIGHT + " TEXT NOT NULL  );";


    public HarvestRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Harvest findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_HARVESTDATE,
                        COLUMN_TOTALWEIGHT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Harvest harvest = new Harvest.Builder()
                    .harvestID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .harvestDate(cursor.getString(cursor.getColumnIndex(COLUMN_HARVESTDATE)))
                    .totalWeight(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALWEIGHT)))
                    .build();

            return harvest;
        } else {
            return null;
        }
    }

    @Override
    public Harvest save(Harvest entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHarvestID());
        values.put(COLUMN_HARVESTDATE, entity.getHarvestDate());
        values.put(COLUMN_TOTALWEIGHT, entity.getWeight());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Harvest insertedEntity = new Harvest.Builder()
                .copy(entity)
                .harvestID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Harvest update(Harvest entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getHarvestID());
        values.put(COLUMN_HARVESTDATE, entity.getHarvestDate());
        values.put(COLUMN_TOTALWEIGHT, entity.getWeight());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHarvestID())}
        );
        return entity;
    }

    @Override
    public Harvest delete(Harvest entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getHarvestID())});
        return entity;
    }

    @Override
    public Set<Harvest> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Harvest> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Harvest harvest = new Harvest.Builder()
                        .harvestID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .harvestDate(cursor.getString(cursor.getColumnIndex(COLUMN_HARVESTDATE)))
                        .totalWeight(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALWEIGHT)))
                        .build();
                candidates.add(harvest);
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
