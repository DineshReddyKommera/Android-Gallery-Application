package com.example.photoalbum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class CustomAdapter extends
        RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    ArrayList<Integer> imagesList;
    Context context;

    public CustomAdapter(Context context, ArrayList<Integer> imagesList
    ) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item Layout
        View v =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent,
                        false);
        // set the view's size, margins, padding and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,
                                 @SuppressLint("RecyclerView") final int position) {
        // set the data in items
        holder.image.setImageResource(imagesList.get(position));
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}

