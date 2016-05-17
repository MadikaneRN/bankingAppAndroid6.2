package cput.ac.za.bankingapp.repository.client.impl;

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
import cput.ac.za.bankingapp.domain.Client;
import cput.ac.za.bankingapp.repository.client.ClientRepository;

/**
 * Created by Scorpian on 2016-04-22.
 */
public class ClientRepositoryImpl extends SQLiteOpenHelper implements ClientRepository
{

    private static final String TABLE_NAME = "client";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ID_NO = "idNo";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME ="surName";

    //Databse creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME + "("
            +COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_ID_NO + " TEXT NOT NULL , " //Unique also
            +COLUMN_NAME + " TEXT NOT NULL , "
            +COLUMN_SURNAME + " TEXT NOT NULL );";


    public ClientRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,
                DBConstants.DATABASE_VERSION);
    }


    public void open()throws SQLException
    {
        db = this.getWritableDatabase();
    }


    public void close()
    {
        this.close();
    }

    public Client findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]
            {
               COLUMN_ID,
               COLUMN_ID_NO,
               COLUMN_NAME,
               COLUMN_SURNAME }, COLUMN_ID + " =?",
            new String[]{String.valueOf(id)},
            null,null,null,null);
        if(cursor.moveToFirst())
        {
            final  Client client = new Client.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .idNo(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NO)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surName(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .build();
            return client;
        }
        else
        {
            return null;
        }
    }


    @Override
    public Client save(Client entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_ID_NO, entity.getIdNo());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurName());

        long id = db.insertOrThrow(TABLE_NAME, null,values);

        Client insertedEntity = new Client.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public Client update(Client entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_ID_NO,entity.getIdNo());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurName());

        db.update(TABLE_NAME,
                values,
                COLUMN_ID +" =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public Client delete(Client entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Client> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Client> clients = new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if(cursor.moveToFirst())
        do {
             final Client client = new Client.Builder()
                     .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                     .idNo(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NO)))
                     .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                     .surName(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                     .build();
                    clients.add(client);
           }while(cursor.moveToNext());

        return clients;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
