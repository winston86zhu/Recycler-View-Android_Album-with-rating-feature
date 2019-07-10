package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CardImage extends AppCompatActivity {
    public static int userRating;
    public transient Bitmap bitmap;
    public transient Context context;
    public transient Model model;
    public ImageView image;

    public CardImage(String url, Context context, Model m) {
        this.userRating = 0;
        this.context = context;
        this.model = m;
        //this.image = (ImageView) findViewById(R.id.im1);
       // new DownloadImageTask().execute(url);
        image = (ImageView) findViewById(R.id.im1);
       // image.setImageResource(R.drawable.empty_star);
       // new DownloadImageTask().execute(url);
    }


/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_card);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        image = (ImageView) findViewById(R.id.im1);
        new DownloadImageTask().execute(url);

    }*/

/*    public void onClick(View v) {
        this.finish();
    }*/

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
            image.setImageBitmap(result);
        }
    }

}