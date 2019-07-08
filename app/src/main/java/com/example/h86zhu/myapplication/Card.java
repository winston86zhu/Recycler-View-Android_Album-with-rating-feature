package com.example.h86zhu.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.Observable;
import java.util.Observer;

public class Card extends CardView implements Observer {
    public ImageView imv;
    public RatingBar rate;
    public Card(Context c, ImageView imv, RatingBar rtb){
        super(c);
        this.imv = imv;
        this.rate = rtb;
    }

    @Override
    public void update(Observable observable, Object o) {
        this.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate.setRating((int) rating);

            }
        });
    }
}
