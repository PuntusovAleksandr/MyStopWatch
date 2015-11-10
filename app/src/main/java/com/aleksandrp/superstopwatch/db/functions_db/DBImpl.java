package com.aleksandrp.superstopwatch.db.functions_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aleksandrp.superstopwatch.db.DBHelper.MyDBHelper;
import com.aleksandrp.superstopwatch.db.constants.ValuesDB;
import com.aleksandrp.superstopwatch.db.entity.TimeFix;

import java.util.ArrayList;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class DBImpl {

    private MyDBHelper dbHelper;
    private Context context;
    private ContentValues contentValues;
    private SQLiteDatabase database;
    private Cursor cursor;
    private static RefreshList mUpdate;

    public DBImpl(Context context) {
        this.context = context;
        Log.i(ValuesDB.TAG_DB, "Open : " + getClass().getName().toString());
    }

    public DBImpl(Context context, RefreshList updateList) {
        this.context = context;
        Log.i(ValuesDB.TAG_DB, "Open : " + getClass().getName().toString());
        mUpdate = updateList;
    }

    public void openDb() {
        if (dbHelper == null) {
            dbHelper = new MyDBHelper(context);
            database = dbHelper.getWritableDatabase();
            Log.i(ValuesDB.TAG_DB, "Open method openDb - \n dbHelper::: "
                    + dbHelper.toString() + "\ndatabase::: "
                    + database.toString());
        }
    }

    public void close() {
        if (dbHelper != null) dbHelper.close();
        Log.i(ValuesDB.TAG_DB, "dbHelper isxlosed");
    }

    private void refresh() {
        if (mUpdate != null) {
            mUpdate.refreshListHistory();
        }
    }

    public interface RefreshList {
        void refreshListHistory();
    }

    public void putNewTime(TimeFix timeFix) {
        openDb();
        Log.i(ValuesDB.TAG_DB, "putNewTime where timeFix = " + timeFix.toString());
        contentValues = new ContentValues();
        contentValues.put(ValuesDB.COLUMN_TITLE, timeFix.getTitle());
        contentValues.put(ValuesDB.COLUMN_DATE, timeFix.getDate());
        contentValues.put(ValuesDB.COLUMN_TIME_DATA, timeFix.getTimeLong());
        try {
            database.insert(ValuesDB.NAME_TABLE_TABLE_TIME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();
    }


    public ArrayList<TimeFix> getAllTimeById() {
        Log.i(ValuesDB.TAG_DB, "getAllTimeById");
        return getAllTimeFixes(ValuesDB.COLUMN_ID);
    }

    public ArrayList<TimeFix> getAllTimeByTitle() {
        Log.i(ValuesDB.TAG_DB, "getAllTimeByTitle");
        return getAllTimeFixes(ValuesDB.COLUMN_TITLE);
    }

    public ArrayList<TimeFix> getAllTimeByDate() {
        Log.i(ValuesDB.TAG_DB, "getAllTimeByDate");
        return getAllTimeFixes(ValuesDB.COLUMN_DATE);
    }

    public ArrayList<TimeFix> getAllTimeByTime() {
        Log.i(ValuesDB.TAG_DB, "getAllTimeByTime");
        return getAllTimeFixes(ValuesDB.COLUMN_TIME_DATA);
    }

    @NonNull
    private ArrayList<TimeFix> getAllTimeFixes(String orderBy) {
        ArrayList<TimeFix> timeFixes = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(ValuesDB.NAME_TABLE_TABLE_TIME,
                    null, null, null, null, null, orderBy + " DESC");
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(ValuesDB.COLUMN_ID));
                    String title = cursor.getString(cursor.getColumnIndex(ValuesDB.COLUMN_TITLE));
                    long date = cursor.getLong(cursor.getColumnIndex(ValuesDB.COLUMN_DATE));
                    long timeLong = cursor.getLong(cursor.getColumnIndex(ValuesDB.COLUMN_TIME_DATA));
                    timeFixes.add(new TimeFix(id, title, date, timeLong));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        }
        return timeFixes;
    }

    public void removeAllTime() {
        openDb();
        Log.i(ValuesDB.TAG_DB, "removeAllTime all ");
        database.delete(ValuesDB.NAME_TABLE_TABLE_TIME, null, null);
        refresh();
    }


    public void removeById(long l) {
        openDb();
        Log.i(ValuesDB.TAG_DB, "removeById where time = " + l);
        database.delete(ValuesDB.NAME_TABLE_TABLE_TIME,
                ValuesDB.COLUMN_ID + " = " + l, null);
        refresh();
    }

}
