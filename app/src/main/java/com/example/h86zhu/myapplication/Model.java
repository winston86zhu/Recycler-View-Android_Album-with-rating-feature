package com.example.h86zhu.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements Serializable{
    public static int selected_star = 0;
    public Context context;
    public ArrayList<ImageView> image_pool;

    public Model(Context context) {
        this.image_pool = new ArrayList<>();
        this.context = context;
    }


    public void pre_load() throws IOException {
        URL url = new URL("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/bunny.jpg");
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        ImageView iv_bunny = new ImageView(context);
        iv_bunny.setImageBitmap(bmp);
        image_pool.add(iv_bunny);
    }

    public void addImage(ImageView image) {
        this.image_pool.add(image);
        this.notifyObservers();
    }

    public void clear_image() {
        this.image_pool = new ArrayList<>();
        notifyObservers();
    }


    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }
}
