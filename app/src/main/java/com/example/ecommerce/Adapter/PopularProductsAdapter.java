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
import com.example.ecommerce.Models.PopularProductsModel;
import com.example.ecommerce.R;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {
   private Context context;

    public PopularProductsAdapter(Context context, List<PopularProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    private List<PopularProductsModel> list;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popiular_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.popularImg);
        holder.popularName.setText(list.get(position).getName());
        holder.popularprice.setText(String.valueOf(list.get(position).getPrice()));
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
TextView popularName,popularprice;
ImageView popularImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularImg= itemView.findViewById(R.id.all_img);
            popularName= itemView.findViewById(R.id.all_product_name);
            popularprice= itemView.findViewById(R.id.all_price);
        }
    }
}
