package com.aleksandrp.superstopwatch.db.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aleksandrp.superstopwatch.db.constants.ValuesDB;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        super(context, ValuesDB.NAME_DB, null, ValuesDB.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ValuesDB.TAG_DB, "Start ::: " + ValuesDB.CREATE_TABLE);

        db.execSQL(ValuesDB.CREATE_TABLE);

        Log.i(ValuesDB.TAG_DB, "Finish ::: " + ValuesDB.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
