package com.aleksandrp.mystopwatch.db.constants;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public abstract class ValuesDB {

    public static final String TAG_DB = "Tag_db";


    public static final String NAME_DB = "time_count";
    public static final int VERSION_DB = 1;
    public static final String NAME_TABLE_TABLE_TIME = "table_time";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DATE = "time";
    public static final String COLUMN_TIME_DATA = "time_data";

    public static final String CREATE_TABLE = "CREATE TABLE "
            + NAME_TABLE_TABLE_TIME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TITLE + " TEXT, "
            + COLUMN_DATE + " INTEGER NOT NULL, "
            + COLUMN_TIME_DATA + " INTEGER NOT NULL);";

}
