package com.example.h86zhu.myapplication;

import android.content.Context;
import android.widget.RatingBar;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable{
    public int selected_star = 0;
    public transient Context context;
    public ArrayList<CardImage> card_pool;
    public transient ArrayList<IView> observers;

    String[] urls = {
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/bunny.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/chinchilla.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/doggo.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/fox.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/hamster.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/husky.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/kitten.png",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/loris.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/puppy.jpg",
            "https://www.student.cs.uwaterloo.ca/~cs349/s19/assignments/images/sleepy.png"
    };

    public Model(Context context) {
        this.card_pool = new ArrayList<>();
        this.context = context;
        this.observers = new ArrayList<>();
    }




    public void pre_load() throws IOException {

        for(int i = 0; i < 10; i++) {
            CardImage cdi = new CardImage(urls[i], context, this);

            Card cd = new Card(context, cdi);
            cd.rate = (RatingBar) cd.findViewById(R.id.b1);
            this.card_pool.add(cdi);
        }
        notifyViews();
    }



    public void clear_image() {
        this.card_pool = new ArrayList<>();
        notifyViews();
    }


    public void addObserver(IView view) {
        observers.add(view);
    }

    public void notifyViews() {
        for (IView view : observers) {
            view.updateView();
        }
    }
}
