package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Category;
import com.otto.beekeeperstocksystem.Repositories.CategoryRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class CategoryRepositoryImpl extends SQLiteOpenHelper implements CategoryRepository {
    public static final String TABLE_NAME = "category";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "categoryID";
    public static final String COLUMN_STOCKTYPE = "stockType";
    public static final String COLUMN_BEEKEEPER = "beekeeper";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_STOCKTYPE + " TEXT NOT NULL  );";


    public CategoryRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Category findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_STOCKTYPE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Category category = new Category.Builder()
                    .categoryID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .stockType(cursor.getDouble(cursor.getColumnIndex(COLUMN_STOCKTYPE)))
                    .build();

            return category;
        } else {
            return null;
        }
    }

    @Override
    public Category save(Category entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCategoryId());
        values.put(COLUMN_STOCKTYPE, entity.getstockType());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Category insertedEntity = new Category.Builder()
                .copy(entity)
                .categoryID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Category update(Category entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getCategoryId());
        values.put(COLUMN_STOCKTYPE, entity.getstockType());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCategoryId())}
        );
        return entity;
    }

    @Override
    public Category delete(Category entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getCategoryId())});
        return entity;
    }

    @Override
    public Set<Category> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Category> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Category category = new Category.Builder()
                        .categoryID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .stockType(cursor.getDouble(cursor.getColumnIndex(COLUMN_STOCKTYPE)))
                        .build();
                candidates.add(category);
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

