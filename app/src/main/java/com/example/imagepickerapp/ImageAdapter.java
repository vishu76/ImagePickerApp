package com.example.imagepickerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.myViewHolder> {
    public ArrayList<ImageModel> arrayList;
    public Context context;

    public ImageAdapter(ArrayList<ImageModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_layout,parent,false);
        return new ImageAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ImageModel mylist=arrayList.get(position);
        holder.imageTitle.setHorizontallyScrolling(true);
        holder.imageTitle.setSelected(true);
        holder.imageTitle.setText(mylist.getImagename());
        holder.image.setImageURI(mylist.getImageUri());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView imageTitle;
        ImageView image;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageTitle = itemView.findViewById(R.id.tvtitle);
            image = itemView.findViewById(R.id.img_product);
        }
    }
}
