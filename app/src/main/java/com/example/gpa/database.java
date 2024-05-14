package com.example.gpa;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public final Context context;
    public static final String DATABASE_NAME = "gpa.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_REUSLT = "result";
    public static final String COLUMN_SUB_CODE = "SUB_CODE";
    public static final String COLUMN_CREDITS = "Credits";
    public static final String COLUMN_RESULT = "Results";






    public database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //members
        String queryMembers = "CREATE TABLE " + TABLE_REUSLT +
                "(" + COLUMN_SUB_CODE + " INTEGER PRIMARY KEY," +
                COLUMN_CREDITS + " INTEGER," +
                COLUMN_RESULT + " TEXT )";
        db.execSQL(queryMembers);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REUSLT);
        onCreate(db);
    }


    void addsub(String subcode, String credit , String results) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUB_CODE, subcode);
        cv.put(COLUMN_CREDITS, credit);
        cv.put(COLUMN_RESULT, results);

        long result = db.insert(TABLE_REUSLT, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed to add msub", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Sub added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Read all members from the library
    Cursor readsub() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_REUSLT, null, null, null, null, null, null);
    }

    // Update a member in the library
    void updatesub(String subcode, String credit , String results) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CREDITS, credit);
        cv.put(COLUMN_RESULT, results);

        long result = db.update(TABLE_REUSLT, cv, COLUMN_SUB_CODE + "=?", new String[]{subcode});
        if (result == -1) {
            Toast.makeText(context, "Failed to update SUb", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SUb updated successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // Delete a member from the library
    void deleteMember(String subcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_REUSLT, COLUMN_SUB_CODE + "=?", new String[]{subcode});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete Sub", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SUb deleted successfully", Toast.LENGTH_SHORT).show();
        }
    }


}

