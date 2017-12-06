package com.example.minesweeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.minesweeper.R.layout.activity_main;

public class hardMode extends AppCompatActivity {
    CountDownTimer cdt;
    static boolean countFlagButton = false;
    Button buttonFlag;
    TextView[][] textButton;
    TextView textCountFlag;
    TextView textTime;
    boolean[][] arrFlag;
    static int countFlag = 0, countCorrectFlag = 0, countTimer = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        getSupportActionBar().setTitle("Minesweeper (Hard)");
        Button buttonClear = (Button) findViewById(R.id.buttonClear);
        buttonFlag = (Button) findViewById(R.id.buttonFlag);
        textCountFlag = (TextView) findViewById(R.id.textCountFlag);
        textTime = (TextView) findViewById(R.id.textViewTimer);
        textButton = new TextView[10][10];
        arrFlag = new boolean[10][10];
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                String sID = "textView" + i + j;
                int ids = getResources().getIdentifier(sID, "id", getPackageName());
                textButton[i][j] = (TextView) findViewById(ids);
            }
        }
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                textButton[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = view.getId();
                        int indexI = 0;
                        int indexJ = 0;
                        for (int i = 1; i <= 9; i++) {
                            for (int j = 1; j <= 9; j++) {
                                if (textButton[i][j].getId() == id) {
                                    indexI = i;
                                    indexJ = j;
                                    break;
                                }
                            }
                            if (indexI != 0) break;
                        }

                        if (arrFlag[indexI][indexJ]) {
                            arrFlag[indexI][indexJ] = false;
                            textButton[indexI][indexJ].setCompoundDrawables(null, null, null, null);
                            countFlag--;
                            textCountFlag.setText("Flag : " + countFlag);
                            if (textButton[indexI][indexJ].getText().equals("B")) {
                                countCorrectFlag--;
                            }
                        } else if (countFlag >= 10) {
                            buttonFlag.setBackgroundResource(R.drawable.flag);
                            countFlagButton = false;
                        } else if (countFlagButton && countFlag < 10) {
                            countFlag++;
                            textCountFlag.setText("Flag : " + countFlag);
                            arrFlag[indexI][indexJ] = true;
                            textButton[indexI][indexJ].setCompoundDrawablesWithIntrinsicBounds(
                                    R.drawable.flag, 0, 0, 0);
                            if (textButton[indexI][indexJ].getText().equals("B")) {
                                countCorrectFlag++;
                            }
                            if (countCorrectFlag == 10) {
                                cdt.cancel();
                                //ShowDialog("WINNER!!!!!","YOU SAFE");
                                Intent intent = new Intent(hardMode.this,add_score.class);
                                String onlyTime = textTime.getText().toString().substring(7);
                                int score = Integer.parseInt(onlyTime);
                                intent.putExtra("score",score);
                                intent.putExtra("mode","hard");
                                startActivity(intent);

                            }
                        } else {

                            Click(indexI, indexJ);
                        }
                    }
                });
            }
        }
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cdt != null) {
                    cdt.cancel();
                }

                cdt = new CountDownTimer(201000, 1000) {
                    @Override
                    public void onTick(long milli) {
                        long sec = (milli / 1000);
                        textTime.setText(String.valueOf("Time : " + sec));
                    }

                    @Override
                    public void onFinish() {
                        textTime.setText("TimeOver!!");
                        cancel();
                        ShowDialog("BTOOOOOOOM!!!!!","YOU DIE");
                    }

                }.start();

                Reset();

            }

        });
        buttonFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (countFlagButton || countFlag >= 10) {
                    countFlagButton = false;
                } else {
                    countFlagButton = true;
                }
                if (countFlagButton) {
                    buttonFlag.setBackgroundResource(R.drawable.flagpress);
                } else {
                    buttonFlag.setBackgroundResource(R.drawable.flag);

                }
            }
        });

    }

    public void ShowDialog(String title,String result) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(hardMode.this);
        dialog.setTitle(title);
        dialog.setMessage(result);
        dialog.setNeutralButton("HOMEPAGE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(hardMode.this, HomePage.class);
                startActivity(intent);
            }
        });
        dialog.setPositiveButton("TRY AGAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cdt.cancel();
                if (cdt != null) {
                    cdt.cancel();
                }

                cdt = new CountDownTimer(201000, 1000) {
                    @Override
                    public void onTick(long milli) {
                        long sec = (milli / 1000);
                        textTime.setText(String.valueOf("Time : " + sec));
                    }

                    @Override
                    public void onFinish() {
                        textTime.setText("TimeOver!!");
                        cancel();
                        ShowDialog("BTOOOOOOOM!!!!!","YOU DIE");
                    }

                }.start();

                Reset();
            }
        });
        dialog.setNegativeButton("OUT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                moveTaskToBack(true);
            }
        });
        dialog.show();
    }

    public void Click(int indexI, int indexJ) {
        if (textButton[indexI][indexJ].getText().equals("B")) {
            ShowBomb();
            cdt.cancel();
            ShowDialog("BTOOOOOOOM!!!!!","YOU DIE");
        } else if (textButton[indexI][indexJ].getText().equals("0")) {
            textButton[indexI][indexJ].setTextColor(Color.TRANSPARENT);
            textButton[indexI][indexJ].setBackgroundResource(R.drawable.table11);
            if (indexI > 1 && indexI <= 8 && indexJ > 1 && indexJ <= 8) {
                OpenAround(indexI, indexJ);
            }
        } else if (textButton[indexI][indexJ].getText().equals("1")) {
            textButton[indexI][indexJ].setTypeface(null, Typeface.BOLD);
            textButton[indexI][indexJ].setTextColor(Color.parseColor("#002fdb"));
        } else if (textButton[indexI][indexJ].getText().equals("2")) {
            textButton[indexI][indexJ].setTypeface(null, Typeface.BOLD);
            textButton[indexI][indexJ].setTextColor(Color.parseColor("#1e753c"));
        } else {
            textButton[indexI][indexJ].setTypeface(null, Typeface.BOLD);
            textButton[indexI][indexJ].setTextColor(Color.parseColor("#d80824"));
        }
    }

    public void OpenAround(int indexI, int indexJ) {
        for (int i = indexI - 1; i <= indexI + 1; i++) {
            for (int j = indexJ - 1; j <= indexJ + 1; j++) {
                if (textButton[i][j].getText().equals("0")) {
                    textButton[i][j].setTextColor(Color.TRANSPARENT);
                    textButton[i][j].setBackgroundResource(R.drawable.table11);
                } else if (textButton[i][j].getText().equals("1")) {
                    textButton[i][j].setTypeface(null, Typeface.BOLD);
                    textButton[i][j].setTextColor(Color.parseColor("#002fdb"));
                } else if (textButton[i][j].getText().equals("2")) {
                    textButton[i][j].setTypeface(null, Typeface.BOLD);
                    textButton[i][j].setTextColor(Color.parseColor("#1e753c"));
                } else {
                    textButton[i][j].setTypeface(null, Typeface.BOLD);
                    textButton[i][j].setTextColor(Color.parseColor("#d80824"));
                }
            }
        }
    }

    public void ShowBomb() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (textButton[i][j].getText().equals("B")) {
                    textButton[i][j].setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.bomb, 0, 0, 0);
                }
            }
        }
    }

    public void Reset() {
        countFlagButton = false;
        countFlag = 0;
        countCorrectFlag = 0;
        textTime.setText("Time : 200");
        textCountFlag.setText("Flag : 0");
        buttonFlag.setBackgroundResource(R.drawable.flag);
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                arrFlag[i][j] = false;
                textButton[i][j].setText("");
                textButton[i][j].setCompoundDrawables(null, null, null, null);
                textButton[i][j].setTextColor(Color.TRANSPARENT);
                textButton[i][j].setBackgroundColor(Color.TRANSPARENT);
            }
        }
        SetBomb();
    }

    public void SetBomb() {
        int countBomb = 0;
        while (countBomb < 10) {
            int i = (int) (Math.random() * 9) + 1;
            int j = (int) (Math.random() * 9) + 1;
            if (textButton[i][j].getText().length() == 0) {
                countBomb++;
                textButton[i][j].setText("B");

            }
        }
        countBomb();
    }

    public void countBomb() {

        int countAround = 0;
        //OutsideFrame
        //Left
        for (int i = 2; i <= 8; i++) {
            countAround = 0;
            if (!textButton[i][1].getText().equals("B")) {
                if (textButton[i - 1][1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i - 1][2].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i][2].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i + 1][1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i + 1][2].getText().equals("B")) {
                    countAround++;
                }
                textButton[i][1].setText(String.valueOf(countAround));
            }
            countAround = 0;
            //Right
            if (!textButton[i][9].getText().equals("B")) {
                if (textButton[i - 1][9].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i - 1][8].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i][8].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i + 1][8].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[i + 1][9].getText().equals("B")) {
                    countAround++;
                }
                textButton[i][9].setText(String.valueOf(countAround));
            }
        }
        for (int j = 2; j <= 8; j++) {
            //Top
            countAround = 0;
            if (!textButton[1][j].getText().equals("B")) {
                if (textButton[1][j - 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[1][j + 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[2][j - 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[2][j].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[2][j + 1].getText().equals("B")) {
                    countAround++;
                }
                textButton[1][j].setText(String.valueOf(countAround));
            }
            countAround = 0;
            //Under
            if (!textButton[9][j].getText().equals("B")) {
                if (textButton[9][j - 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[9][j + 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[8][j - 1].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[8][j].getText().equals("B")) {
                    countAround++;
                }
                if (textButton[8][j + 1].getText().equals("B")) {
                    countAround++;
                }
                textButton[9][j].setText(String.valueOf(countAround));
            }
        }
        //4angle
        countAround = 0;
        if (!textButton[1][1].getText().equals("B")) {
            if (textButton[1][2].getText().equals("B")) {
                countAround++;
            }
            if (textButton[2][1].getText().equals("B")) {
                countAround++;
            }
            if (textButton[2][2].getText().equals("B")) {
                countAround++;
            }
            textButton[1][1].setText(String.valueOf(countAround));
        }
        countAround = 0;
        if (!textButton[1][9].getText().equals("B")) {
            if (textButton[1][8].getText().equals("B")) {
                countAround++;
            }
            if (textButton[2][8].getText().equals("B")) {
                countAround++;
            }
            if (textButton[2][9].getText().equals("B")) {
                countAround++;
            }
            textButton[1][9].setText(String.valueOf(countAround));
        }
        countAround = 0;
        if (!textButton[9][1].getText().equals("B")) {
            if (textButton[8][1].getText().equals("B")) {
                countAround++;
            }
            if (textButton[8][2].getText().equals("B")) {
                countAround++;
            }
            if (textButton[9][2].getText().equals("B")) {
                countAround++;
            }
            textButton[9][1].setText(String.valueOf(countAround));
        }
        countAround = 0;
        if (!textButton[9][9].getText().equals("B")) {
            if (textButton[8][9].getText().equals("B")) {
                countAround++;
            }
            if (textButton[8][8].getText().equals("B")) {
                countAround++;
            }
            if (textButton[9][8].getText().equals("B")) {
                countAround++;
            }
            textButton[9][9].setText(String.valueOf(countAround));
        }
        //Inbox
        for (int i = 2; i <= 8; i++) {
            for (int j = 2; j <= 8; j++) {
                countAround = 0;
                if (!textButton[i][j].getText().equals("B")) {
                    if (textButton[i - 1][j - 1].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i - 1][j].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i - 1][j + 1].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i][j - 1].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i][j + 1].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i + 1][j - 1].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i + 1][j].getText().equals("B")) {
                        countAround++;
                    }
                    if (textButton[i + 1][j + 1].getText().equals("B")) {
                        countAround++;
                    }
                    textButton[i][j].setText(String.valueOf(countAround));
                }
            }
        }

    }


}
