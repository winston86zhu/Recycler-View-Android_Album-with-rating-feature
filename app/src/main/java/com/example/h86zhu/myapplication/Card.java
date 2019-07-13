package com.example.h86zhu.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
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

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("HHHHHHH lCIKEd");
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.image_click);
                ImageView iv = (ImageView) dialog.findViewById(R.id.im1);

                iv.setImageBitmap(imv.bitmap);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.setCancelable(true);
                dialog.show();

            }
        });





        this.rate = findViewById(R.id.b1);
//        rate.setRating(imv.userRating);
        System.out.println("This updateview is called");
        this.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar rate, float rating, boolean fromUser) {
                imv.userRating = (int) rating;
                rate.setRating((int) rating);
                MainActivity.model.notifyViews();
            }
        });

        ImageView iv = (ImageView) this.findViewById(R.id.im1);
        iv.setImageBitmap(this.imv.bitmap);
    }

}
