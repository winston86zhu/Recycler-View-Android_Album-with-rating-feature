package com.example.h86zhu.myapplication;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class Card_Adpater extends RecyclerView.Adapter<Card_Adpater.ViewHolder>{

//    private Context my_context;
    public Model model;
    public ArrayList<ImageView> imv_arr;
    public ArrayList<CardImage> filtered_card;

    public Card_Adpater(Model m) {
//        this.my_context = c;
        this.model = m;
        //this.card_arr = m.card_pool;
//        this.card_arr = new ArrayList<>();
        imv_arr = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final CardImage cdi = filtered_card.get(position);
        holder.bind(cdi);
        holder.imv.setImageBitmap(cdi.bitmap);


        //final ImageView cd_imv = (ImageView) binding_root.findViewById(R.id.im1);
        holder.imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("HHHHHHH lCIKEd");
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
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                dialog.setCancelable(true);
                dialog.show();

            }
        });
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
