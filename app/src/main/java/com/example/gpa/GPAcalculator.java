package com.example.gpa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GPAcalculator {

    private final database dbHelper;

    public GPAcalculator(database dbHelper) {
        this.dbHelper = dbHelper;
    }

    public double calculateGPA() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readsub();

        double totalCredits = 0;
        double totalGradePoints = 0;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int credits = cursor.getInt(cursor.getColumnIndex(database.COLUMN_CREDITS));
                String result = cursor.getString(cursor.getColumnIndex(database.COLUMN_RESULT));
                double gradePoint = convertResultToGradePoint(result);
                totalCredits += credits;
                totalGradePoints += credits * gradePoint;
            } while (cursor.moveToNext());

            cursor.close();
        }

        if (totalCredits == 0) return 0; // to avoid division by zero

        return totalGradePoints / totalCredits;
    }

    private double convertResultToGradePoint(String result) {
        // You need to implement your own logic here to convert the result to grade points
        // This could be a simple mapping or a more complex logic based on your grading system
        // For example, you could use if-else or switch-case statements
        // Return the appropriate grade point for each result
        return 0.0; // Default value, replace with your logic
    }
}
