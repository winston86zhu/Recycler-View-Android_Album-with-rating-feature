package com.example.h86zhu.myapplication;

import android.content.Context;
import android.provider.Telephony;
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
    public ArrayList<CardImage> filtered_card;

    public Card_Adpater(Context c, Model m) {
        this.my_context = c;
        this.model = m;
        //this.card_arr = m.card_pool;
        this.card_arr = new ArrayList<>();

        refresh_image();
    }



    public void refresh_image(){
        this.filtered_card = new ArrayList<>();
        if(model.selected_star == 0){
            filtered_card = model.card_pool;
        } else {
            for(CardImage cd: this.model.card_pool){
                if (cd.userRating >= model.selected_star) {
                    this.filtered_card.add(cd);
                }
            }
        }
        System.out.println("filtered card list has " + filtered_card.size());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_card, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CardImage cdi = filtered_card.get(position);
        holder.imv.setImageBitmap(cdi.bitmap);
        holder.rtb.setNumStars(cdi.userRating);


        //holder.rtb = cd.rate;
        //holder.cardv = cd;

        //((Card) holder.cardv).updateView();
    }

    @Override
    public int getItemCount() {
        return filtered_card.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //public CardView cardv;
        public ImageView imv;
        public int ratings;
        public RatingBar rtb;

        public ViewHolder(View v) {


            super(v);

            imv =  (ImageView) v.findViewById(R.id.im1);
            //ratings = ((RatingBar) v.findViewById(R.id.b1)).getNumStars();
            rtb = (RatingBar) v.findViewById(R.id.b1);
            //rtb.setNumStars(ratings);
            //rtb = v.findViewById(R.id.b1);
            //cardv = v.findViewById(R.id.cd_view);

        }

    }

}
