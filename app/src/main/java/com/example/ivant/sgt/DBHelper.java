package com.example.ivant.sgt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ivant on 04.01.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SGT";
    public static final String TABLE_COLORS = "colors";

    public static final String KEY_ID = "_id";
    public static final String cRed = "red";
    public static final String cGreen = "green";
    public static final String cBlue = "blue";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_COLORS + "(" + KEY_ID + " integer primary key, "
                + cRed + " integer, " + cGreen + " integer, " + cBlue + " integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_COLORS);

        onCreate(db);

    }
}
