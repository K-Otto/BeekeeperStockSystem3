package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;
import com.otto.beekeeperstocksystem.Domain.Product;
import com.otto.beekeeperstocksystem.Repositories.ProductRepository;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by Quam on 4/22/2016.
 */
public class ProductRepositoryImpl extends SQLiteOpenHelper implements ProductRepository {
    public static final String TABLE_NAME = "product";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "productID";
    public static final String COLUMN_TOTALSTOCK = "totalStock";
    public static final String COLUMN_TOTALSTOCKREMAINING = "totalStockRemaining";
    public static final String COLUMN_CATEGORY = "subLocation";
    public static final String COLUMN_HARVESTS = "subLocation";



    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_TOTALSTOCK + " TEXT NOT NULL , "
            + COLUMN_TOTALSTOCKREMAINING + " TEXT NOT NULL , "
            + COLUMN_CATEGORY + " TEXT NOT NULL , "
            + COLUMN_HARVESTS + " TEXT NOT NULL  );";


    public ProductRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Product findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_TOTALSTOCK,
                        COLUMN_TOTALSTOCKREMAINING,
                        COLUMN_CATEGORY,
                        COLUMN_HARVESTS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Product product = new Product.Builder()
                    .productID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .totalStock(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSTOCK)))
                    .totalStockRemaining(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSTOCKREMAINING)))
                    .build();

            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product save(Product entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getProductID());
        values.put(COLUMN_TOTALSTOCK, entity.getTotalStock());
        values.put(COLUMN_TOTALSTOCKREMAINING, entity.getTotalStockRemaining());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Product insertedEntity = new Product.Builder()
                .copy(entity)
                .productID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Product update(Product entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getProductID());
        values.put(COLUMN_TOTALSTOCK, entity.getTotalStock());
        values.put(COLUMN_TOTALSTOCKREMAINING, entity.getTotalStockRemaining());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getProductID())}
        );
        return entity;
    }

    @Override
    public Product delete(Product entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getProductID())});
        return entity;
    }

    @Override
    public Set<Product> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Product> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Product product = new Product.Builder()
                        .productID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .totalStock(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSTOCK)))
                        .totalStockRemaining(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTALSTOCKREMAINING)))
                        .build();
                candidates.add(product);
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

