package com.sp.userfitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userinfo.db";
    private static final int SCHEMA_VERSION = 1;

    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user_data(_id INTEGER PRIMARY KEY AUTOINCREMENT,mood_str TEXT,custom_str TEXT, date TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /*public Cursor getAll() {
        return (getReadableDatabase().rawQuery(
                "SELECT _id, mood_str,custom_str,date FROM user_data ORDER BY _id", null));
    }*/


    public void insert(String mood_str, String custom_str, String date) {
        ContentValues cv = new ContentValues();
        cv.put("mood_str", mood_str);
        cv.put("custom_str", custom_str);
        cv.put("date", date);

        getWritableDatabase().insert("user_data", null, cv);
    }

    public Cursor getLatestData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query =  "SELECT " + "mood_str" + ", " + "custom_str" +","+ "date" +
                " FROM " + "user_data" +
                " ORDER BY " + "date" + " DESC LIMIT 1";
        return db.rawQuery(query, null);
    }

    public Cursor getDataByDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + "mood_str" + ", " + "custom_str" +", "+ "date" +
        " FROM " + "user_data" + " WHERE DATE(" + "date" + ") = DATE(?)"+
                " ORDER BY " + "date" + " DESC";
        return db.rawQuery(query, new String[]{date});
    }

    /*public List<String[]> getDataByDate(String selectedDate){
        List<String[]> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {"mood_str", "custom_str", "date"};
        String selection = "date LIKE ?"; // 'LIKE' allows partial matching for date filtering
        String[] selectionArgs = {selectedDate + "%"}; // '%' is a wildcard for matching any time
        String orderBy = "date DESC";

        Cursor cursor = db.query("user_data", columns, selection, selectionArgs, null, null, orderBy);
        if(cursor!= null){
            Set<String> uniqueDates = new HashSet<>(); // Track unique dates
            while(cursor.moveToNext()){
                String mood_str = cursor.getString(cursor.getColumnIndex("mood_str"));
                String custom_str = cursor.getString(cursor.getColumnIndex("custom_str"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                String dateOnly = date.split("")[0];
                if (!uniqueDates.contains(dateOnly)) {
                    dataList.add(new String[]{mood_str, custom_str, date});
                    uniqueDates.add(dateOnly); // Mark this date as processed
                }
            }

        }
            cursor.close();
        return dataList;
    };
    public String[] getDataForSelectedDate(String selectedDate){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"mood_str", "custom_str", "date"};
        String selection = "date = ?";
        String[] selectionArgs = {selectedDate};
        String sortOrder = "date DESC";

        Cursor cursor = db.query(
                "user_data",   // The table to query
                projection,    // The columns to return
                selection,     // The columns for the WHERE clause
                selectionArgs, // The values for the WHERE clause
                null,          // Group the rows
                null,          // Filter by row groups
                sortOrder);
        String[] result = null;
        if (cursor != null && cursor.moveToFirst()) {
            String mood_str = cursor.getString(cursor.getColumnIndex("mood_str"));
            String custom_str = cursor.getString(cursor.getColumnIndex("custom_str"));
            String date = cursor.getString(cursor.getColumnIndex("date"));

            result = new String[]{mood_str, custom_str, date};
        }

        if (cursor != null) {
            cursor.close();
        }

        return result;

    }*/





    public String getmood_str(Cursor c) {
        return (c.getString(1));
    }

    public String getcustom_str(Cursor c) {
        return (c.getString(2));
    }

    public String getdate(Cursor c) {
        return (c.getString(3));
    }
}