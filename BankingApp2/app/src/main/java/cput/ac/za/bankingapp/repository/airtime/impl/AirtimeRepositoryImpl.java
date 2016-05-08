package cput.ac.za.bankingapp.repository.airtime.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import cput.ac.za.bankingapp.conf.database.DBConstants;
import cput.ac.za.bankingapp.domain.Airtime;
import cput.ac.za.bankingapp.repository.Repository;
import cput.ac.za.bankingapp.repository.airtime.AirtimeRepository;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class AirtimeRepositoryImpl extends SQLiteOpenHelper implements AirtimeRepository {

    public static final String TABLE_NAME = "airtime";
    private SQLiteDatabase db;



    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CELLPHONE_NO = "cellphoneNo";
    public static final String COLUMN_BENEFICIARY = "beneficiary";
    public static final String COLUMN_SERVICE_PROVIDER = "serviceProvider";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CELLPHONE_NO + " TEXT NOT NULL , "
            + COLUMN_BENEFICIARY + " TEXT NOT NULL , "
            + COLUMN_SERVICE_PROVIDER + " TEXT NOT NULL );";




    public AirtimeRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void close() {
        this.close();
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }


    @Override
    public Airtime findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                    COLUMN_ID,
                    COLUMN_CELLPHONE_NO,
                    COLUMN_SERVICE_PROVIDER,
                    COLUMN_BENEFICIARY },
                COLUMN_ID +" =? ",
               new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor.moveToFirst())
        {
            final Airtime airtime =new Airtime.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .beneficiary(cursor.getString(cursor.getColumnIndex(COLUMN_BENEFICIARY)))
                    .cellphoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE_NO)))
                    .serviceProvider(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE_PROVIDER)))
                    .build();

             return airtime;

        }
        else
        {
            return null;
        }


    }

    @Override
    public Airtime save(Airtime entity) {
        return null;
    }

    @Override
    public Airtime update(Airtime entity) {
        return null;
    }

    @Override
    public Airtime delete(Airtime entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Airtime> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Airtime> airtimes = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);

       if(cursor.moveToFirst())
       {
           do
           {
               final Airtime airtime = new Airtime.Builder()
                       .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                       .beneficiary(cursor.getString(cursor.getColumnIndex(COLUMN_BENEFICIARY)))
                       .cellphoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_CELLPHONE_NO)))
                       .serviceProvider(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE_PROVIDER)))
                       .build();
               airtimes.add(airtime);
           }while(cursor.moveToNext());
       }

        return airtimes;
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
