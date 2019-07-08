package com.example.h86zhu.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class Card_Adpater extends BaseAdapter {

    private Context my_context;
    public Model model;
    //public ArrayList<CardView> card_arr;
    public ArrayList<Card> filtered_card;

    public Card_Adpater(Context c, Model m) {
        this.my_context = c;
        this.model = m;
        //this.card_arr = new ArrayList<>();
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
    public int getCount() {
        return filtered_card.size();
    }

    @Override
    public Object getItem(int i) {
        return this.filtered_card.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long)i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
