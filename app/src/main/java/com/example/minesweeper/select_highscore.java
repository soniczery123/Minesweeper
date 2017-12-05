package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class select_highscore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_highscore);
        Button selectEASY = (Button) findViewById(R.id.buttonSelect_EASY);
        Button selectHARD = (Button) findViewById(R.id.buttonSelect_HARD);
        selectEASY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(select_highscore.this, highscore_easy.class);
                startActivity(intent);

            }
        });
        selectHARD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(select_highscore.this, highscore_hard.class);
                startActivity(intent);
            }
        });
    }
}
