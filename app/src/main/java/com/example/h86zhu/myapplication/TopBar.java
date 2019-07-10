package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class TopBar implements Observer {

    public ArrayList<ImageButton> star_arr;
    public Context context;
    public View backingView;
    public Model model;
    public static boolean loaded = false;


    public TopBar(Context c, Model m){
        model = m;
        context = c;

        this.backingView = ((Activity) c).findViewById(R.id.action_bar);
        this.star_arr = new ArrayList<>();
        this.star_arr.add((ImageButton) this.backingView.findViewById(R.id.star0));
        this.star_arr.add((ImageButton) this.backingView.findViewById(R.id.star1));
        this.star_arr.add((ImageButton) this.backingView.findViewById(R.id.star2));
        this.star_arr.add((ImageButton) this.backingView.findViewById(R.id.star3));
        this.star_arr.add((ImageButton) this.backingView.findViewById(R.id.star4));


        for (int i = 0; i < 5; ++i) {
            this.star_arr.get(i).setOnClickListener(new StarListener(i));
            this.star_arr.get(i).setTag(i);
        }

        ImageButton clearButton = (ImageButton)this.backingView.findViewById(R.id.clear);
        ImageButton loadButton = (ImageButton)this.backingView.findViewById(R.id.load);
        ImageButton refreshButton = (ImageButton)this.backingView.findViewById(R.id.refresh);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.clear_image();
                loaded = false;
                Snackbar.make(backingView.getRootView().findViewById(R.id.cardList), "Cleared images", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                model.refresh_image();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loaded) {
                    try {

                        model.pre_load();
                        System.out.println(model.card_pool.size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(backingView.getRootView().findViewById(R.id.cardList),
                            "Loaded images", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    loaded = true;
                } else {
                    Snackbar.make(backingView.getRootView().findViewById(R.id.cardList), "Images already loaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.selected_star = 0;
            }
        });

    }

    private class StarListener implements View.OnClickListener {
        int pos;
        public StarListener (int position) {
            pos = position;
        }

        @Override
        public void onClick(View v) {
            Model.selected_star = (pos+1);
            for (int i=0; i<model.selected_star; ++i) {
                star_arr.get(i).setImageResource(android.R.drawable.star_on);
            }
            for (int i=model.selected_star; i<5; ++i) {
                star_arr.get(i).setImageResource(android.R.drawable.star_off);
            }
        }
    }



    @Override
    public void update(Observable observable, Object o) {
        Drawable emptyStar = ContextCompat.getDrawable(context, R.drawable.empty_star);
        Drawable filledStar = ContextCompat.getDrawable(context, R.drawable.filled_star);
        Resources res;


    }
}
