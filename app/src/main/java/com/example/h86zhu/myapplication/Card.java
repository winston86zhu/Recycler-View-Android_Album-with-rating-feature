package com.example.h86zhu.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.RatingBar;

public class Card extends CardView implements IView {
    public CardImage imv;
    public RatingBar rate;
    public Context ct;


    public Card(Context c, final CardImage imv) {
        super(c);
        this.imv = imv;
        ct = c;
    }



    @Override
    public void updateView() {





        this.rate = findViewById(R.id.b1);
//        rate.setRating(imv.userRating);
        this.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                imv.userRating = (int) rating;
                rate.setRating((int) rating);
                //MainActivity.update
                //MainActivity.model.notifyViews();

                //((Card_Adpater)MainActivity.recList.getAdapter()).refresh_image();
                //((Card_Adpater)MainActivity.recList.getAdapter()).notifyDataSetChanged();
                //MainActivity.tbar.model.notifyViews();
                imv.model.notifyViews();
                //MainActivity.updateViews()
                //imv.notifyAll();
            }
        });

        ImageView iv = (ImageView) this.findViewById(R.id.im1);
        iv.setImageBitmap(this.imv.bitmap);
    }

}
