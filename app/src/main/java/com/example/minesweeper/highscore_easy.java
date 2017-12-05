package com.example.minesweeper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.minesweeper.adapter.HighScoreListAdapter;
import com.example.minesweeper.db.HighScoreDbHelper;
import com.example.minesweeper.model.HighScoreItem;

import java.util.ArrayList;

import static com.example.minesweeper.db.HighScoreDbHelper.COL_ID;
import static com.example.minesweeper.db.HighScoreDbHelper.COL_NAME;
import static com.example.minesweeper.db.HighScoreDbHelper.COL_SCORE;

public class highscore_easy extends AppCompatActivity {
    private HighScoreDbHelper mHelper;
    private SQLiteDatabase mDb;
    private HighScoreListAdapter mAdapter;
    private ArrayList<HighScoreItem> mHighScoreItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_easy);
        getSupportActionBar().setTitle("High Score");
        mHelper = new HighScoreDbHelper(this);
        mDb = mHelper.getReadableDatabase();
        final Button buttonHomepage = (Button)findViewById(R.id.buttonHomeEasy);
        Cursor cursor = mDb.query(HighScoreDbHelper.TABLE_NAME_EASY,null,null,null,null,null, COL_SCORE + " DESC");
        int i = 1;
        while (cursor.moveToNext()) {
            if(i>10) break;
            int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            int score = cursor.getInt(cursor.getColumnIndex(COL_SCORE));
            HighScoreItem item = new HighScoreItem(id, i+". "+name, score);
            mHighScoreItemList.add(item);
            i++;
        }
        mAdapter = new HighScoreListAdapter(
                this,
                R.layout.activity_item,
                mHighScoreItemList
        );
        ListView lv = (ListView) findViewById(R.id.listScore);
        lv.setAdapter(mAdapter);
        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(highscore_easy.this,HomePage.class);
                startActivity(intent);
            }
        });
    }
}
