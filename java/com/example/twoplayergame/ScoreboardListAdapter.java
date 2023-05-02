package com.example.twoplayergame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ScoreboardListAdapter extends ArrayAdapter<Statistics> {
    private Context mContext;
    private  int mResource;
    public ScoreboardListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Statistics> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource,parent,false);

        TextView titleNum = convertView.findViewById(R.id.TitleNum);
        TextView redScore = convertView.findViewById(R.id.redScore);
        TextView blueScore = convertView.findViewById(R.id.blueScore);

        titleNum.setText(getItem(position).getGames());
        redScore.setText(getItem(position).getRedScore());
        blueScore.setText(getItem(position).getBlueScore());

        return convertView;
    }
}
