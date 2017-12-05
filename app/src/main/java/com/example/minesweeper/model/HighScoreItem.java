package com.example.minesweeper.model;

/**
 * Created by MSI-GL62 on 26/11/2560.
 */

public class HighScoreItem {
    public final int id;
    public final String name;
    public final int score;

    public HighScoreItem(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
}
