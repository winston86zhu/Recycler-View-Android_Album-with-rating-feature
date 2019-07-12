package com.example.h86zhu.myapplication;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.RatingBar;

public class Card extends CardView implements IView {
    public CardImage imv;
    public RatingBar rate;
    public Context ct;


    public Card(Context c, CardImage imv){
        super(c);
        this.imv = imv;
        ct = c;

    }



    @Override
    public void updateView() {
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

    //new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

    /*Someone posted the source from StackOveFlow*/

}
