package cput.ac.za.bankingapp.repository.bank.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cput.ac.za.bankingapp.conf.database.DBConstants;
import cput.ac.za.bankingapp.domain.Bank;
import cput.ac.za.bankingapp.repository.bank.BankRepository;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class BankRepositoryImpl extends SQLiteOpenHelper implements BankRepository {

    private static final String TABLE_NAME = "bank";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_NAME = "name";

    //Database creation sql statement
    private static final String DATABAE_CREATE = " CREATE TABLE "
            +TABLE_NAME+ " ("
            +COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_CODE+ " TEXT NOT NULL , "
            +COLUMN_NAME+ " TEXT NOT NULL );";

    public BankRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,
                DBConstants.DATABASE_VERSION);
    }


    public void open()throws SQLException
    {
        db = this.getWritableDatabase();
    }



    public void close() {
        this.close();
    }

    public Bank delete(Bank entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }


    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }


    @Override
    public Bank findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]
                {
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_NAME },COLUMN_ID + " =?",
                        new String[]{String.valueOf(id)},
                null,null,null,null);

                if(cursor.moveToFirst())
                {
                    final Bank bank = new Bank.Builder()
                            .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                            .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                            .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                            .build();
                    return bank;
                }
                else
                {
                    return null;
                }


    }

    @Override
    public Bank save(Bank entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_NAME,entity.getName());
        long id = db.insertOrThrow(TABLE_NAME, null,values);

        Bank insertedEntity = new Bank.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public Bank update(Bank entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_CODE,entity.getCode());
        values.put(COLUMN_NAME,entity.getName());
        db.update(TABLE_NAME,
                values,
                COLUMN_ID +" =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Bank> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Bank> banks = new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if(cursor.moveToFirst())

        do{
            final Bank bank = new Bank.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();

        }while(cursor.moveToNext());

        return banks;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABAE_CREATE);
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
