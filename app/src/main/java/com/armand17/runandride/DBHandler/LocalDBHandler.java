package com.armand17.runandride.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by armand17 on 06/10/15.
 */
public class LocalDBHandler extends SQLiteOpenHelper {

    /**
     * Database name
     */
    private static String DBNAME = "DatabaseTracking";

    /**
     * Version number of the database
     */
    private static int VERSION = 1;

    /**
     * Field 1 of the table locations, which is the primary key
     */
    public static final String FIELD_ROW_ID = "id";

    public static final String FIELD_USER_ID = "user_id";

    /**
     * Field 2 of the table locations, stores the latitude
     */
    public static final String FIELD_LAT = "lat";

    /**
     * Field 3 of the table locations, stores the longitude
     */
    public static final String FIELD_LNG = "lng";

    /**
     * Field 4 of the table locations, stores the zoom level of map
     */
    public static final String FIELD_ZOOM = "zoom";

    public static final String FIELD_TIME = "time";

    public static final String FIELD_SESSION_NAME = "session";

    /**
     * A constant, stores the the table name
     */
    private static final String DATABASE_TABLE = "tracking";

    /**
     * An instance variable for SQLiteDatabase
     */
    private SQLiteDatabase mDB;


    public LocalDBHandler(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + DATABASE_TABLE + " ( " +
                FIELD_ROW_ID + "integer primary key autoincrement , " +
                FIELD_USER_ID + " integer , " +
                FIELD_LAT + " double , " +
                FIELD_LNG + " double , " +
                FIELD_ZOOM + " text , " +
                FIELD_TIME + " datetime , " +
                FIELD_SESSION_NAME + " text " +
                " ) ";
        db.execSQL(sql);
    }

    /**
     * insert new point location
     */
    public long insert(ContentValues contentValues) {
        long rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    public int del() {
        int cnt = mDB.delete(DATABASE_TABLE, null, null);
        return cnt;
    }

    public Cursor getAllLocations() {
        return mDB.query(DATABASE_TABLE, new String[]{FIELD_ROW_ID, FIELD_LAT, FIELD_LNG, FIELD_ZOOM, FIELD_TIME, FIELD_SESSION_NAME}, null, null, null, null, null);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
