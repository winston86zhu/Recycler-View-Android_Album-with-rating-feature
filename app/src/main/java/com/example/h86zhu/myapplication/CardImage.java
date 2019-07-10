package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CardImage extends AppCompatActivity {
    public static int userRating;
    public String url;
    public transient Bitmap bitmap;
    public transient Context context;
    public transient Model model;
    public ImageView image;

    public CardImage(String url, Context context, Model m) {
        this.url = url;
        this.userRating = 0;
        this.context = context;
        this.model = m;
        //this.image = (ImageView) findViewById(R.id.im1);
       // new DownloadImageTask().execute(url);
        new DownloadImageTask().execute(url);


    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        //private CardImage imageModel;
//        public DownloadImageTask(CardImage imageModel) {
//            this.imageModel = imageModel;
//        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bm = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                bm = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bm;
        }

        protected void onPostExecute(Bitmap result) {
            bitmap = result;
        }
    }

}