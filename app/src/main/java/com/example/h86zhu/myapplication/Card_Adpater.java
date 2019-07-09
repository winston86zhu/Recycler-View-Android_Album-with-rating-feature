package com.example.h86zhu.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Card_Adpater extends RecyclerView.Adapter<Card_Adpater.ViewHolder>{

    private Context my_context;
    public Model model;
    public ArrayList<Card> card_arr;
    public ArrayList<Card> filtered_card;

    public Card_Adpater(Context c, Model m) {
        this.my_context = c;
        this.model = m;
        this.card_arr = new ArrayList<>();
        this.filtered_card = new ArrayList<>();
        refresh_image();
    }

    public void refresh_image(){
        if(Model.selected_star == 0){
            //filtered_card = card_arr;
            filtered_card = model.card_pool;
        } else {
            for(Card cd: model.card_pool){
                if (cd.rate.getNumStars() >= model.selected_star) {
                    this.filtered_card.add(cd);
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card cd = filtered_card.get(position);
        holder.card = filtered_card.get(position);
    }

    @Override
    public int getItemCount() {
        return filtered_card.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected Card card;

        public ViewHolder(View v) {
            super(v);
            card = (Card)v.findViewById(R.id.cd_view);
        }

    }

}
