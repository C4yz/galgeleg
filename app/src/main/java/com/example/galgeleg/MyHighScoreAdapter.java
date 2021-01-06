package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyHighScoreAdapter extends RecyclerView.Adapter<MyHighScoreAdapter.MyViewHolder> {

    private ArrayList<String> highScoreList;
    private Context context;

    public MyHighScoreAdapter(ArrayList<String> highScoreList, Context context) {
        this.highScoreList = highScoreList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_score_list_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String line = highScoreList.get(position);

        String[] tokens = line.split(", ");

        holder.playerName.setText(tokens[0]);

        holder.playerScore.setText(tokens[1]);

        holder.relativeLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText( context, highScoreList.get(position), Toast.LENGTH_SHORT ).show();

            }
        } );
    }

    @Override
    public int getItemCount() {
        return highScoreList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView playerName;
        TextView playerScore;
        Button deleteButton;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            playerName = itemView.findViewById(R.id.listPlayerName);
            playerScore = itemView.findViewById(R.id.listPlayerScore);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutList);
        }
    }
}
