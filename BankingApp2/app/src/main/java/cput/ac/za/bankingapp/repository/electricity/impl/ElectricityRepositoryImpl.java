package cput.ac.za.bankingapp.repository.electricity.impl;

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
import cput.ac.za.bankingapp.domain.Electricity;
import cput.ac.za.bankingapp.repository.electricity.ElectricityRepository;

/**
 * Created by Scorpian on 2016-04-24.
 */

//Change my test units to match factory
//=========================Findby Method===================================
public class ElectricityRepositoryImpl extends SQLiteOpenHelper implements ElectricityRepository{

    public static final String TABLE_NAME = "electricity";
    private SQLiteDatabase db;

    private Long id;
    private String meterNo;
    private String supplierName;
    private double amount;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_METER_NO = "meterNo";
    public static final String COLUMN_SUPPLIER_NAME = "supplierName";
    public static final String COLUMN_AMOUNT = "amount";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_METER_NO + " TEXT NOT NULL , "
            + COLUMN_SUPPLIER_NAME + " TEXT NOT NULL , "
            + COLUMN_AMOUNT + " REAL NOT NULL );";


    public ElectricityRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }
    @Override
    public Electricity save(Electricity entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_METER_NO,entity.getId());
        values.put(COLUMN_SUPPLIER_NAME,entity.getId());
        values.put(COLUMN_AMOUNT,entity.getId());
        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Electricity insertedEntity = new Electricity.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;

    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }
    public void close() {
        this.close();
    }
    @Override
    public Electricity findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor  = db.query(
              TABLE_NAME,
              new String[] {
              COLUMN_ID,
              COLUMN_METER_NO,
              COLUMN_SUPPLIER_NAME,
              COLUMN_AMOUNT },
              COLUMN_ID + " =? ",
              new String[]{String.valueOf(id)},
              null,
              null,
              null,
              null);
        if(cursor.moveToFirst())
        {
            final Electricity electricity = new Electricity
                    .Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .meterNo(cursor.getString(cursor.getColumnIndex(COLUMN_METER_NO)))
                    .supplierName(cursor.getString(cursor.getColumnIndex(COLUMN_SUPPLIER_NAME)))
                    .amount(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)))
                    .build();
            return electricity;
        }
        else
        {
            return null;
        }

    }

    @Override
    public Electricity update(Electricity entity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_METER_NO,entity.getMeterNo());
        values.put(COLUMN_SUPPLIER_NAME,entity.getSupplierName());
        values.put(COLUMN_AMOUNT,entity.getAmount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Electricity> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Electricity> electricities = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);

        if(cursor.moveToFirst())
        {
            do
            {
                final Electricity electricity = new Electricity.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .meterNo(cursor.getString(cursor.getColumnIndex(COLUMN_METER_NO)))
                        .supplierName(cursor.getString(cursor.getColumnIndex(COLUMN_SUPPLIER_NAME)))
                        .amount(cursor.getDouble(cursor.getColumnIndex(COLUMN_AMOUNT)))
                        .build();

             electricities.add(electricity);
            }while(cursor.moveToNext());
        }

        return electricities;
    }

    @Override
    public Electricity delete(Electricity entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
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












