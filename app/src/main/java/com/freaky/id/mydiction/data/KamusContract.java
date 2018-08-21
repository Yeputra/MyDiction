package com.freaky.id.mydiction.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.freaky.id.mydiction.data.model.Kamus;

import java.util.ArrayList;

public class KamusContract {
    private static String ENGLISH = DatabaseHelper.TABLE_ENGLISH;
    private static String INDONESIA = DatabaseHelper.TABLE_INDONESIA;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusContract(Context context) {
        this.context = context;
    }

    public KamusContract open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }


    public Cursor queryAllData() {
        String DATABASE_TABLE =ENGLISH;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + DatabaseHelper.FIELD_ID + " ASC", null);
    }
    public Cursor queryAllDataIndo() {
        String DATABASE_TABLE =INDONESIA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + DatabaseHelper.FIELD_ID + " ASC", null);
    }

    public ArrayList<Kamus> getAllData() {
        Kamus kamus;

        ArrayList<Kamus> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamus.setKata(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_KATA)));
                kamus.setTerjemahan(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TERJEMAHAN)));
                arrayList.add(kamus);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<Kamus> getAllDataIndo() {
        Kamus kamus;

        ArrayList<Kamus> arrayList = new ArrayList<>();
        Cursor cursor = queryAllDataIndo();

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamus.setKata(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_KATA)));
                kamus.setTerjemahan(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TERJEMAHAN)));
                arrayList.add(kamus);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public long insert(Kamus kamus) {
        String DATABASE_TABLE = ENGLISH;
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.FIELD_KATA, kamus.getKata());
        initialValues.put(DatabaseHelper.FIELD_TERJEMAHAN, kamus.getTerjemahan());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void insertTransaction(ArrayList<Kamus> kamus) {
        String DATABASE_TABLE = ENGLISH;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                DatabaseHelper.FIELD_KATA + ", " +
                DatabaseHelper.FIELD_TERJEMAHAN + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamus.size(); i++) {
            stmt.bindString(1, kamus.get(i).getKata());
            stmt.bindString(2, kamus.get(i).getTerjemahan());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void insertTransactionIndo(ArrayList<Kamus> kamus) {
        String DATABASE_TABLE = INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                DatabaseHelper.FIELD_KATA + ", " +
                DatabaseHelper.FIELD_TERJEMAHAN + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamus.size(); i++) {
            stmt.bindString(1, kamus.get(i).getKata());
            stmt.bindString(2, kamus.get(i).getTerjemahan());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }
}
