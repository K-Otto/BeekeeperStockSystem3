package com.otto.beekeeperstocksystem.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.otto.beekeeperstocksystem.Conf.Database.DBConstants;

import com.otto.beekeeperstocksystem.Domain.Person;
import com.otto.beekeeperstocksystem.Repositories.PersonRepository;

import java.util.HashSet;
import java.util.Set;



/**
 * Created by Quam on 4/22/2016.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepository {

    public static final String TABLE_NAME = "candidate";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "personId";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL  );";


    public PersonRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Person findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{

                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_EMAIL},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Person candidate = new Person.Builder()
                    .personID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .build();

            return candidate;
        } else {
            return null;
        }
    }

    @Override
    public Person save(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getPersonId());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_EMAIL, entity.getEmail());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .personID(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Person update(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getPersonId());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_EMAIL, entity.getEmail());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getPersonId())}
        );
        return entity;
    }

    @Override
    public Person delete(Person entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getPersonId())});
        return entity;
    }

    @Override
    public Set<Person> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Person> candidates = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Person candidate = new Person.Builder()
                        .personID(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .build();
                candidates.add(candidate);
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