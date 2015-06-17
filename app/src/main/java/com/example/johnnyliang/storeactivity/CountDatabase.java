package com.example.johnnyliang.storeactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JohnnyLiang on 6/17/15.
 */
public class CountDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SQLiteCount.db";
    private static final int DATABASE_VERSION = 1;
    private static final String COUNTER_NAME = "counter";
    private static final String COUNTER_NUMBER = "number";

    public CountDatabase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + COUNTER_NAME + "(" +
                        COUNTER_NUMBER + " INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COUNTER_NAME);
        onCreate(db);
    }

    public boolean insertCounter(int count) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNTER_NUMBER, count);
        db.insert(COUNTER_NAME, null, contentValues);
        return true;
    }

    public Cursor getPerson() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + COUNTER_NAME, null);
        return res;
    }
}
