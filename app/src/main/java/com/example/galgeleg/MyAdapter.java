package com.example.galgeleg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> highScoreList;
    private Context context;

    public MyAdapter(ArrayList<String> highScoreList, Context context) {
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
        holder.playerName.setText(highScoreList.get(position));

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
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super( itemView );

            playerName = itemView.findViewById(R.id.listPlayerName);
            playerScore = itemView.findViewById(R.id.listPlayerScore);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutList);
        }
    }
}
