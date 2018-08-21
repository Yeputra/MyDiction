package com.freaky.id.mydiction.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "MyDiction.db";

    public static String TABLE_ENGLISH = "english_word";
    public static String TABLE_INDONESIA = "indonesian_word";

    public static String FIELD_ID = "id";
    public static String FIELD_KATA = "kata";
    public static String FIELD_TERJEMAHAN = "terjemahan";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENGLISH = "create table " + TABLE_ENGLISH + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_KATA + " text not null, " +
            FIELD_TERJEMAHAN + " text not null);";

    public static String CREATE_TABLE_INDONESIA = "create table " + TABLE_INDONESIA + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_KATA + " text not null, " +
            FIELD_TERJEMAHAN + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        db.execSQL(CREATE_TABLE_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA);
        onCreate(db);
    }
}
