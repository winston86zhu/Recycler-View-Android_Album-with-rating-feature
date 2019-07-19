package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;


public class TopBar implements IView {

    public ArrayList<ImageButton> star_arr;
    public Context context;
    public View backingView;
    public Model model;
    public boolean loaded = false;


    public TopBar(Context c, Model m){
        model = m;
        context = c;
        this.model.addObserver(this);

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
                model.selected_star = 0;
                model.notifyViews();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!loaded) {
                    try {
                        model.pre_load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Snackbar.make(backingView.getRootView().findViewById(R.id.cardList),
                            "Images Loaded", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    model.selected_star = 0;
                    loaded = true;
                    model.notifyViews();
                } else {
                    /*
                    Loading and Clearing Button will set current filter to 0
                     */
                    Snackbar.make(backingView.getRootView().findViewById(R.id.cardList), "Reload Image", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    model.selected_star = 0;
                    model.clear_image();
                    try {
                        model.pre_load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    model.notifyViews();

                }
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.selected_star = 0;
                model.notifyViews();
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
            model.selected_star = (pos+1);
            model.notifyViews();
        }
    }



    @Override
    public void updateView() {
        for (int i=0; i<model.selected_star; ++i) {
            star_arr.get(i).setImageResource(android.R.drawable.star_on);
        }
        for (int i=model.selected_star; i<5; ++i) {
            star_arr.get(i).setImageResource(android.R.drawable.star_off);
        }
    }
}
