package com.example.h86zhu.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Card_Adpater extends RecyclerView.Adapter<Card_Adpater.ViewHolder>{

    private Context my_context;
    public Model model;
    public ArrayList<Card> card_arr;
//    public ArrayList<Card> filtered_card;

    public Card_Adpater(Context c, Model m) {
        this.my_context = c;
        this.model = m;
        this.card_arr = m.card_pool;
//        this.card_arr = new ArrayList<>();
//        this.filtered_card = new ArrayList<>();
        model.refresh_image();
    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card cd = card_arr.get(position);
        //holder.cardv = cd.imv.image
        holder.imgv = cd.imv.image;


    }

    @Override
    public int getItemCount() {
        return card_arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected CardView cardv;
        protected ImageView imgv;

        public ViewHolder(View v) {
            super(v);
            cardv = (CardView) v.findViewById(R.id.cd_view);
            imgv = v.findViewById(R.id.im1);
        }

    }

}
