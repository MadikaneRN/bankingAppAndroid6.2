package cput.ac.za.bankingapp.repository.address;

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
import cput.ac.za.bankingapp.domain.Address;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class AddressRepositoryImpl extends SQLiteOpenHelper implements  AddressRepository {

    public static final String TABLE_NAME = "address";
    private SQLiteDatabase db;


    private Long id;
    private String streetName;
    private String city;
    private String postalCode;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STREET_NAME = "streetName";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_POSTAL_CODE = "postalCode";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STREET_NAME + " TEXT NOT NULL , "
            + COLUMN_CITY + " TEXT NOT NULL , "
            + COLUMN_POSTAL_CODE + " TEXT NOT NULL );" ;

    public AddressRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME,
                null, DBConstants.DATABASE_VERSION);
    }


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    @Override
    public Address findById(Long aLong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_STREET_NAME,
                        COLUMN_CITY,
                        COLUMN_POSTAL_CODE },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if(cursor.moveToFirst())
        {
            final Address address = new Address.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREET_NAME)))
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTAL_CODE)))
                    .build();

            return address;
        }
        else
        {
            return null;
        }



    }

    @Override
    public Address save(Address entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_STREET_NAME,entity.getStreetName());
        values.put(COLUMN_CITY,entity.getCity());
        values.put(COLUMN_POSTAL_CODE,entity.getPostalCode());

        long id = db.insertOrThrow(TABLE_NAME,null,values);
        Address insertedEntity = new Address.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;

    }

    @Override
    public Address update(Address entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_STREET_NAME,entity.getStreetName());
        values.put(COLUMN_CITY,entity.getCity());
        values.put(COLUMN_POSTAL_CODE,entity.getPostalCode());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Address delete(Address entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Address> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Address> addresses = new HashSet<>();
        open();

        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if(cursor.moveToNext())
        {
            do{
                final Address address = new Address.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREET_NAME)))
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTAL_CODE)))
                        .build();

                addresses.add(address);

            }while (cursor.moveToNext());
        }

        return addresses;
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
