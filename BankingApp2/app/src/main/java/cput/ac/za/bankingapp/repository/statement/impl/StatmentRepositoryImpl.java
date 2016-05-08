package cput.ac.za.bankingapp.repository.statement.impl;

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
import cput.ac.za.bankingapp.domain.Statement;
import cput.ac.za.bankingapp.repository.statement.StatementRepository;

/**
 * Created by Scorpian on 2016-04-24.
 */
public class StatmentRepositoryImpl extends SQLiteOpenHelper implements StatementRepository {

    private static final String TABLE_NAME = "statement";
    private SQLiteDatabase db;

    private String details;
    private String weekandDay;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_WEEK_AND_DAY = "weekandDay";

    //Databse creation sql statement
    private static final String DATABAE_CREATE = " CREATE TABLE "
            +TABLE_NAME+ " ("
            +COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_DETAILS+ " TEXT  NOT NULL , "
            +COLUMN_WEEK_AND_DAY+ " TEXT NOT NULL );";


    public StatmentRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME,null,
                DBConstants.DATABASE_VERSION);
    }


    public void open() throws SQLException
    {
        db = this.getWritableDatabase();
    }

    @Override
    public  void close() {
        this.close();
    }

    @Override
    public Statement findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]
                        {
                                COLUMN_ID,
                                COLUMN_DETAILS,
                                COLUMN_WEEK_AND_DAY
                        }, COLUMN_ID + " =?",
                new String[]{String.valueOf(id)},
                null,null,null,null);
        if(cursor.moveToFirst())
        {
            final  Statement statement = new Statement.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .details(cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS)))
                    .weekandDay(cursor.getString(cursor.getColumnIndex(COLUMN_WEEK_AND_DAY)))
                    .build();
            return statement;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Statement save(Statement entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_DETAILS,entity.getDetails());
        values.put(COLUMN_WEEK_AND_DAY,entity.getWeekandDay());

        long id = db.insertOrThrow(TABLE_NAME, null,values);

        Statement insertedEntity = new Statement.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return insertedEntity;

    }

    @Override
    public Statement update(Statement entity) {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_DETAILS,entity.getDetails());
        values.put(COLUMN_WEEK_AND_DAY,entity.getWeekandDay());

        db.update(TABLE_NAME,
                values,
                COLUMN_ID +" =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;

    }

    @Override
    public Statement delete(Statement entity) {
        open();
        db.delete(TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Set<Statement> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Statement> statements = new HashSet<>();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if(cursor.moveToFirst())
            do {
                final Statement statment = new Statement.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .details(cursor.getString(cursor.getColumnIndex(COLUMN_DETAILS)))
                        .weekandDay(cursor.getString(cursor.getColumnIndex(COLUMN_WEEK_AND_DAY)))
                        .build();
                statements.add(statment);
            }while(cursor.moveToNext());
        return statements;
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
