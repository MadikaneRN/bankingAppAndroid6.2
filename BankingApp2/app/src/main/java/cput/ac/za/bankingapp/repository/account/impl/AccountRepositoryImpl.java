package cput.ac.za.bankingapp.repository.account.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cput.ac.za.bankingapp.conf.database.DBConstants;
import cput.ac.za.bankingapp.domain.Account;
import cput.ac.za.bankingapp.repository.account.AccountRepository;

/**
 * Created by Scorpian on 2016-04-23.
 *
 * COMPLETE FINDBYID
 */
public class AccountRepositoryImpl extends SQLiteOpenHelper implements AccountRepository {

    private static final String TABLE_NAME = "account";
    private SQLiteDatabase db;

    /*
    private Long id;
    private String accNo;
    private double balance;
    private String accountType;
     *  */

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCNO = "accNo";
    public static final String COLUMN_BALANCE ="balance";
    public static final String COLUMN_ACCOUNT_TYPE ="accountType";

    private static final String DATABASE_CREATE = " CREATE "
            + TABLE_NAME + "("
            + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACCNO + " TEXT NOT NULL , "
            +COLUMN_BALANCE + " REAL NOT NULL , "
            +COLUMN_ACCOUNT_TYPE + " TEXT NOT NULL);";

    public AccountRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,
                DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException
    {
        db = this.getWritableDatabase();
    }

    @Override
    public Account findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]
                {
                    COLUMN_ID,COLUMN_ACCNO,COLUMN_BALANCE,
                        COLUMN_ACCOUNT_TYPE }, COLUMN_ID +" =? ",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor.moveToFirst())
        {

            final Account account = new Account.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .accNo(cursor.getString(cursor.getColumnIndex(COLUMN_ACCNO)))
                    .balance(cursor.getDouble(cursor.getColumnIndex(COLUMN_BALANCE)))
                    .accountType(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_TYPE)))
                    .build();
            return  account;
        }
        else
        {
            return null;
        }


    }

    @Override
    public Account save(Account entity) {
        open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ACCNO,entity.getAccNo());
        values.put(COLUMN_BALANCE,entity.getBalance());
        values.put(COLUMN_ACCOUNT_TYPE,entity.getAccountType());
        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Account insertedEntity = new Account.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }



    @Override
    public Account update(Account entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ACCNO,entity.getAccNo());
        values.put(COLUMN_BALANCE,entity.getBalance());
        values.put(COLUMN_ACCOUNT_TYPE, entity.getAccountType());
        db.update(TABLE_NAME,values,COLUMN_ID +" =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Account delete(Account entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;



    }

    @Override
    public Set<Account> findAll() {

        SQLiteDatabase db = this.getReadableDatabase();
        Set<Account> accounts = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            do {
                final Account account = new Account.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .accNo(cursor.getString(cursor.getColumnIndex(COLUMN_ACCNO)))
                        .balance(cursor.getDouble(cursor.getColumnIndex(COLUMN_BALANCE)))
                        .accountType(cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT_TYPE)))
                        .build();
                accounts.add(account);
            } while (cursor.moveToNext());
        }
        return accounts;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
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
