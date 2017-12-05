package com.example.minesweeper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.minesweeper.db.HighScoreDbHelper;

import static com.example.minesweeper.db.HighScoreDbHelper.COL_NAME;
import static com.example.minesweeper.db.HighScoreDbHelper.COL_SCORE;

public class add_score extends AppCompatActivity {
    private String name;
    private HighScoreDbHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        mHelper = new HighScoreDbHelper(this);
        mDb = mHelper.getReadableDatabase();
        final EditText editTextName = (EditText) findViewById(R.id.editTextName);
        Button buttonAccept = (Button) findViewById(R.id.buttonAccept);
        TextView yourScore = (TextView) findViewById(R.id.textViewScore);
        Intent intent = getIntent();
        final int score = intent.getIntExtra("score", 0);
        final String mode = intent.getStringExtra("mode");

        yourScore.setText("Score : " + score);
        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = String.valueOf(editTextName.getText().toString());
                if (name.length() == 0) {
                    editTextName.setError("ป้อนชื่อ");
                } else if(name.length()>=8){
                    editTextName.setError("ความยาวชื่อต้องน้อยกว่า 8 ตัวอักษร");
                }else if (mode.equals("easy")) {
                    ContentValues cv = new ContentValues();
                    cv.put(COL_NAME, name);
                    cv.put(COL_SCORE, score);
                    mDb.insert(HighScoreDbHelper.TABLE_NAME_EASY,null,cv);
                    Intent intentToHighScore = new Intent(add_score.this, highscore_easy.class);
                    startActivity(intentToHighScore);
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(COL_NAME, name);
                    cv.put(COL_SCORE, score);
                    mDb.insert(HighScoreDbHelper.TABLE_NAME_HARD,null,cv);
                    Intent intentToHighScore = new Intent(add_score.this, highscore_hard.class);
                    startActivity(intentToHighScore);
                }
            }
        });
    }
}
