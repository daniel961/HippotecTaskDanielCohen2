package com.app.daniel.hippotectask_daniel_cohen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<Flower> FlowerList;
    Context context;

    public RecyclerViewAdapter(Context context, List<Flower> flowerList) {
        FlowerList = flowerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Fname_TV.setText(FlowerList.get(position).getFname().toString());
        holder.Season_TV.setText(FlowerList.get(position).getFbestSeason().toString());
        Picasso.get().load(FlowerList.get(position).getUrl()).into(holder.IV_FlowerPicture);

    }

    @Override
    public int getItemCount() {
        return FlowerList.size();
    }









    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Fname_TV;
        TextView Season_TV;
        ImageView IV_FlowerPicture;
        RelativeLayout ItemParentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            Fname_TV = itemView.findViewById(R.id.Fname_TV);
            Season_TV = itemView.findViewById(R.id.Season_TV);
            IV_FlowerPicture = itemView.findViewById(R.id.IV_FlowerPicture);
            ItemParentLayout = itemView.findViewById(R.id.ItemParentLayout);
        }


    }
}