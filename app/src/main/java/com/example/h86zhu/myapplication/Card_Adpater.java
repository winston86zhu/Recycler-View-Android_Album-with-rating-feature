package com.example.h86zhu.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class Card_Adpater extends RecyclerView.Adapter<Card_Adpater.ViewHolder>{

    public Model model;
    public ArrayList<ImageView> imv_arr;
    public ArrayList<CardImage> filtered_card;
    public Context c;
    public View Main_View;

    public Card_Adpater(Context con, Model m) {
        this.model = m;
        imv_arr = new ArrayList<>();
        refresh_image();
        this.c = con;
        Main_View = (((Activity) con).findViewById(R.id.action_bar)).getRootView();
    }



    public void refresh_image(){
        this.filtered_card = new ArrayList<>();
        if(model.selected_star == 0){
            filtered_card = model.card_pool;
        } else {
            for(CardImage cd: this.model.card_pool){
                if (cd.userRating >= model.selected_star) {
                    this.filtered_card.add(cd);
                }
            }
        }
        System.out.println("filtered card list has " + filtered_card.size());
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // Instantiates a layout XML file into its corresponding View objects
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.single_card, parent, false);

        ViewHolder pvh = new ViewHolder(binding);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CardImage cdi = filtered_card.get(position);



        //final ImageView cd_imv = (ImageView) binding_root.findViewById(R.id.im1);
        holder.imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(holder.imv.getContext());
                dialog.setContentView(R.layout.image_click);
                ImageView iv = (ImageView) dialog.findViewById(R.id.iclicker);

                iv.setImageBitmap(cdi.bitmap);
                //iv.setImageDrawable(cd_imv.getDrawable());
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(true);
                Snackbar.make(Main_View.findViewById(R.id.cardList), "Image Zoom-in", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                dialog.show();
            }
        });

        holder.imb.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cdi.userRating = 0;
                holder.ratingBar.setRating(0);
                Snackbar.make(Main_View.findViewById(R.id.cardList), "Cleared Individual Rating", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println("HHHHA CHANGE RATE");
                cdi.userRating = (int) rating;
                holder.ratingBar.setRating(rating);
                //Snackbar.make(Main_View.findViewById(R.id.cardList), "Rating Changed", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                model.notifyViews();
            }
        });

        holder.bind(cdi);
        holder.imv.setImageBitmap(cdi.bitmap);
    }

    @Override
    public int getItemCount() {
        return filtered_card.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //public CardView cardv;
        public ImageView imv;
        public RatingBar ratingBar;
        public ImageButton imb;
        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            imv =  (ImageView) binding.getRoot().findViewById(R.id.im1);
            ratingBar = (RatingBar) binding.getRoot().findViewById(R.id.b1);
            imb = (ImageButton) binding.getRoot().findViewById(R.id.imb1);

           /* ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    System.out.println("HHHHA CHANGE RATE");
                    MainActivity.model.notifyViews();

                }
            });*/

        }
        public void bind(Object obj) {
            binding.setVariable(BR.rating, obj);
            binding.executePendingBindings();
        }

    }

}
