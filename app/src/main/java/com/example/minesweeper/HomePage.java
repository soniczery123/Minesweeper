package com.example.minesweeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button easyMode = (Button) findViewById(R.id.easyButton);
        Button hardMode = (Button) findViewById(R.id.hardButton);
        Button HowToPlayButton = (Button) findViewById(R.id.HowToPlayButton);
        Button HighScoreButton = (Button) findViewById(R.id.highScoreButton);
        easyMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, easyMode.class);
                startActivity(intent);
            }
        });
        hardMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, hardMode.class);
                startActivity(intent);
            }
        });
        HowToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, how_to_play.class);
                startActivity(intent);
            }
        });
        HighScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, select_highscore.class);
                startActivity(intent);
            }
        });

    }
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
