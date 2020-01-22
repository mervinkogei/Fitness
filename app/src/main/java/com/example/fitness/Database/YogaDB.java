package com.example.fitness.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class YogaDB extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "Yoga.db";
    private static final int DATABASE_VERSION = 1;

    public YogaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public int getSettingMode() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Mode"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();

        return c.getInt(c.getColumnIndex("Mode"));
    }

    public void saveSettingMode(int value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Mode = " + value;
        db.execSQL(query);
    }
}