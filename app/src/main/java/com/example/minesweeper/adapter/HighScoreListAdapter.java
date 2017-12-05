package com.example.minesweeper.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.minesweeper.R;
import com.example.minesweeper.model.HighScoreItem;

import java.util.ArrayList;

/**
 * Created by MSI-GL62 on 26/11/2560.
 */

public class HighScoreListAdapter extends ArrayAdapter<HighScoreItem> {
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<HighScoreItem> mHighscoreList;

    public HighScoreListAdapter(Context mContext, int mLayoutResId, ArrayList<HighScoreItem> mHighscoreList) {
        super(mContext,mLayoutResId,mHighscoreList);
        this.mContext = mContext;
        this.mLayoutResId = mLayoutResId;
        this.mHighscoreList = mHighscoreList;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemLayout = inflater.inflate(mLayoutResId, null);

        HighScoreItem item = mHighscoreList.get(position);

        TextView playerName = itemLayout.findViewById(R.id.textViewName);
        TextView playerScore = itemLayout.findViewById(R.id.textViewScore);

        playerName.setText(item.name);
        playerScore.setText(String.valueOf(item.score));
        if(position == 0){
            playerName.setTextColor(Color.parseColor("#FFC125"));
            playerScore.setTextColor(Color.parseColor("#FFC125"));
            playerName.setTypeface(Typeface.DEFAULT_BOLD);
            playerScore.setTypeface(Typeface.DEFAULT_BOLD);
            playerName.setTextSize(20);
            playerScore.setTextSize(20);
        }


        return itemLayout;
    }
}
