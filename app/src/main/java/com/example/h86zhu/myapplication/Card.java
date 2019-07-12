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

public class Card extends CardView implements IView {
    public CardImage imv;
    public RatingBar rate;
    public Context ct;
    public int user_rate;


    public Card(Context c, CardImage imv){
        super(c);
        this.imv = imv;
        ct = c;
    }



    @Override
    public void updateView() {
        this.rate = (RatingBar) this.findViewById(R.id.b1);
        rate.setRating(imv.userRating);
        this.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                imv.userRating = (int) rating;
                imv.model.notifyViews();
                user_rate =(int) rating;
                rate.setRating((int) rating);
            }
        });

        ImageView iv = (ImageView) this.findViewById(R.id.im1);
        iv.setImageBitmap(this.imv.bitmap);

        this.user_rate = rate.getNumStars();

    }

    //new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");

    /*Someone posted the source from StackOveFlow*/

}
