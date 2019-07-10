package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;


import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class CardImage extends ViewModel {
    public static int userRating;
    public int id;
    public String url;
    public transient Bitmap bitmap;
    public transient Context context;
    public transient Model model;

    public CardImage(String url, Context context, Model m) {
        this.url = url;
        this.userRating = 0;
        this.context = context;
        this.model = m;
        new DownloadImageTask(this).execute(url);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private CardImage imageModel;
        public DownloadImageTask(CardImage imageModel) {
            this.imageModel = imageModel;
        }

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
            this.imageModel.bitmap = result;
        }
    }

}