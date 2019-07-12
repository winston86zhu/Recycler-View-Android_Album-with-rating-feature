package com.example.h86zhu.myapplication;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class Card_Adpater extends RecyclerView.Adapter<Card_Adpater.ViewHolder>{

//    private Context my_context;
    public Model model;
//    public ArrayList<Card> card_arr;
    public ArrayList<CardImage> filtered_card;

    public Card_Adpater(Context c, Model m) {
//        this.my_context = c;
        this.model = m;
        //this.card_arr = m.card_pool;
//        this.card_arr = new ArrayList<>();

        refresh_image();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardImage cdi = filtered_card.get(position);
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
        public RatingBar rtb;
        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            imv =  (ImageView) binding.getRoot().findViewById(R.id.im1);
            rtb = (RatingBar) binding.getRoot().findViewById(R.id.b1);

        }
        public void bind(Object obj) {
            binding.setVariable(BR.rating, obj);
            binding.executePendingBindings();
        }

    }

}
