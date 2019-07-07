package com.example.h86zhu.myapplication;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class TopBar implements Observer {
    public ArrayList<ImageButton> star_arr;
    public Context context;
    public View backingView;
    public ColorSpace.Model model;



    @Override
    public void update(Observable observable, Object o) {

    }
}
