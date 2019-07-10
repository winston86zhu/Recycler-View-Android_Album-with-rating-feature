package com.example.h86zhu.myapplication;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

public class Card extends CardView implements Observer {
    public CardImage imv;
    public static RatingBar rate;
    public Context ct;
    public MutableLiveData<Bitmap []> bits;


    public Card(Context c, CardImage imv){
        super(c);
        this.imv = imv;
        ct = c;

    }



    @Override
    public void update(Observable observable, Object o) {
        this.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                imv.userRating = (int) rating;
                rate.setRating((int) rating);

            }
        });
    }

    //new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

    /*Someone posted the source from StackOveFlow*/

}
