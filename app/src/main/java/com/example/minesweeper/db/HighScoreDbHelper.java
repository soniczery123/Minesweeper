package com.example.minesweeper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MSI-GL62 on 26/11/2560.
 */

public class HighScoreDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "high.score";
    private static final int DATABASE_VERSION = 102;

    public static final String TABLE_NAME_HARD = "highscore_hard";
    public static final String TABLE_NAME_EASY = "highscore_easy";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_SCORE = "score";

    private static final String CREATE_TABLE_HARD = "CREATE TABLE " + TABLE_NAME_HARD + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_SCORE + " INTEGER)";
    private static final String CREATE_TABLE_EASY = "CREATE TABLE " + TABLE_NAME_EASY + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_SCORE + " INTEGER)";

    public HighScoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EASY);
        db.execSQL(CREATE_TABLE_HARD);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "PETER");
        cv.put(COL_SCORE, 25);
        db.insert(TABLE_NAME_HARD, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "CHESTER");
        cv.put(COL_SCORE, 20);
        db.insert(TABLE_NAME_HARD, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "GRILL");
        cv.put(COL_SCORE, 15);
        db.insert(TABLE_NAME_HARD, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "CRYSTAL");
        cv.put(COL_SCORE, 10);
        db.insert(TABLE_NAME_HARD, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "DELL");
        cv.put(COL_SCORE, 5);
        db.insert(TABLE_NAME_HARD, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "PETER");
        cv.put(COL_SCORE, 10);
        db.insert(TABLE_NAME_EASY, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "CHESTER");
        cv.put(COL_SCORE, 8);
        db.insert(TABLE_NAME_EASY, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "GRILL");
        cv.put(COL_SCORE, 6);
        db.insert(TABLE_NAME_EASY, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "CRYSTAL");
        cv.put(COL_SCORE, 4);
        db.insert(TABLE_NAME_EASY, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "ALEX");
        cv.put(COL_SCORE, 1);
        db.insert(TABLE_NAME_EASY, null, cv);
        cv = new ContentValues();
        cv.put(COL_NAME, "DELL");
        cv.put(COL_SCORE, 2);
        db.insert(TABLE_NAME_EASY, null, cv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EASY);
        onCreate(db);
    }
}
