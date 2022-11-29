package com.example.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

public class SliderAdapter extends  SliderViewAdapter<SliderAdapter.Holder>{
    String[] images;
    public SliderAdapter(String[] images){
        this.images=images;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
        //viewHolder.imageView.setImageResource(images[position]);
        Picasso.get().load(images[position]).into(viewHolder.imageView);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;
        public Holder(View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
        }
    }
}
