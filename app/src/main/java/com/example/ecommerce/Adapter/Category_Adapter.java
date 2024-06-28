package com.example.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerce.Activities.ShowAllAcitivity;
import com.example.ecommerce.Models.Category_Model;
import com.example.ecommerce.R;

import java.util.List;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
   private Context context;
   private List<Category_Model> list   ;

    public Category_Adapter(Context context, List<Category_Model> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Category_Adapter.ViewHolder holder, int position) {
        // Load image with Glide
        Glide.with(context).load(list.get(holder.getAdapterPosition()).getImg_url()).into(holder.catImg);

        // Set category name
        holder.catName.setText(list.get(holder.getAdapterPosition()).getName());

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the adapter position when the item is clicked

                    Intent intent = new Intent(context, ShowAllAcitivity.class);
                    intent.putExtra("type", list.get(position).getType());
                    context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView catImg;
        TextView catName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg=itemView.findViewById(R.id.cat_img);
            catName= itemView.findViewById(R.id.cat_name);
        }
    }
}
