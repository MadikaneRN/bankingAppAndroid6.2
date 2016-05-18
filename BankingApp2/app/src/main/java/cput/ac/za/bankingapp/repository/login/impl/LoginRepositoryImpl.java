package cput.ac.za.bankingapp.repository.login.impl;

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
import cput.ac.za.bankingapp.domain.Login;
import cput.ac.za.bankingapp.repository.login.LoginRepository;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class LoginRepositoryImpl extends SQLiteOpenHelper implements LoginRepository {

    private static final String TABLE_NAME = "login";
    private SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_PASS_WORD = "passWord";




    //Databse creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            +TABLE_NAME + " ("
            +COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_USER_NAME + " TEXT NOT NULL , "//unique also
            +COLUMN_PASS_WORD + " TEXT NOT NULL );";



    public LoginRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null,
                DBConstants.DATABASE_VERSION);
    }




    public void open()throws SQLException
    {
        db = this.getWritableDatabase();
    }

    @Override
    public Login findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]
                {
                   COLUMN_ID,
                   COLUMN_USER_NAME,
                   COLUMN_PASS_WORD }, COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor.moveToNext())
        {
            final Login login = new Login.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .userName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                    .passWord(cursor.getString(cursor.getColumnIndex(COLUMN_PASS_WORD)))
                    .build();
            return login;
        }
        else
        {
            return null;
        }


    }

    @Override
    public Login save(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_USER_NAME,entity.getUserName());
        values.put(COLUMN_PASS_WORD,entity.getPassWord());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Login insertedEntity = new Login.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;
    }

    @Override
    public Login update(Login entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_USER_NAME, entity.getUserName());
        values.put(COLUMN_PASS_WORD, entity.getPassWord());

        db.update(TABLE_NAME,values,COLUMN_ID +" =? ",new String[]{String.valueOf(entity.getId())});

        return entity;
    }


    @Override
    public Login delete(Login entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }
    @Override
    public Set<Login> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Login> logins = new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if(cursor.moveToFirst())
        do{
            final Login login =new Login.Builder()
            .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
            .passWord(cursor.getString(cursor.getColumnIndex(COLUMN_PASS_WORD)))
                    .userName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)))
                    .build();
            logins.add(login);
        }while(cursor.moveToNext());

        return logins;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public  void close() {
        this.close();
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
