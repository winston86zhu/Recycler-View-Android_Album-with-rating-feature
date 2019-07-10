package com.example.h86zhu.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable implements Serializable{
    public static int selected_star = 0;
    public Context context;
    public ArrayList<Card> card_pool;
    public ArrayList<Card> filtered_card;
    String[] urls = {
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/bunny.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/chinchilla.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/doggo.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/fox.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/hamster.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/husky.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/kitten.png",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/loris.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/puppy.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/sleepy.png"
    };

    public Model(Context context) {
        this.card_pool = new ArrayList<>();
        this.context = context;
    }

    public void refresh_image(){
        if(Model.selected_star == 0){
            filtered_card = card_pool;
            filtered_card = card_pool;
        } else {
            for(Card cd: card_pool){
                if (cd.rate.getNumStars() >= selected_star) {
                    this.filtered_card.add(cd);
                }
            }
        }
    }


    public void pre_load() throws IOException {
//        URL url = new URL("https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/bunny.jpg");
//        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        ImageView iv_bunny = new ImageView(context);
//        iv_bunny.setImageBitmap(bmp);
        //image_pool.add(iv_bunny);

        for(int i = 0; i < 10; i++) {
            CardImage cdi = new CardImage(urls[i], context, this);
            Card cd = new Card(context, cdi);
            cd.rate = (RatingBar) cd.findViewById(R.id.b1);
            this.card_pool.add(cd);
        }
    }

    public void addImage(ImageView image) {
        //this.image_pool.add(image);
        this.notifyObservers();
    }

    public void clear_image() {
        this.card_pool = new ArrayList<>();
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
