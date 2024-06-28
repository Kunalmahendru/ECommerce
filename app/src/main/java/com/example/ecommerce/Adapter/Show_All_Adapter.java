package com.example.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.Activities.DetailActivity;
import com.example.ecommerce.Models.ShowAllModel;
import com.example.ecommerce.R;

import java.util.List;

public class Show_All_Adapter extends RecyclerView.Adapter<Show_All_Adapter.ViewHolder> {

    private Context context;
    private List<ShowAllModel> list;

    public Show_All_Adapter(Context context, List<ShowAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Show_All_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Show_All_Adapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.mItemImage);
        holder.mName.setText(list.get(position).getName());
        holder.mcost.setText("$"+ list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("detailed",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mItemImage;
        TextView mcost;
        TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemImage=itemView.findViewById(R.id.item_image);
            mcost=itemView.findViewById(R.id.item_cost);
            mName=itemView.findViewById(R.id.item_nam);
        }
    }
}
