package com.example.ecommerce.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerce.Models.MyCartModel;
import com.example.ecommerce.R;

import java.util.List;

public class MyCartAdapter  extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> myCartAdapterList;
    int totalAmount=0;

    public MyCartAdapter(Context context, List<MyCartModel> myCartAdapterList) {
        this.context = context;
        this.myCartAdapterList = myCartAdapterList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(myCartAdapterList.get(position).getCurrentDate());
        holder.time.setText(myCartAdapterList.get(position).getCurrentTime());
        holder.price.setText(myCartAdapterList.get(position).getProductPrice()+"$");
        holder.name.setText(myCartAdapterList.get(position).getProductName());
        holder.totalPrice.setText(String.valueOf(myCartAdapterList.get(position).getTotalPrice()));
        holder.totalQuantity.setText(String.valueOf(myCartAdapterList.get(position).getTotalQuantity()));
        //Total amoutn pass to cart activity
        totalAmount+= myCartAdapterList.get(position).getTotalPrice();
        Intent intent=new Intent("MyTotalAmount");
        intent.putExtra("totalAmount",totalAmount);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    @Override
    public int getItemCount() {
        return  myCartAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,date,time,totalQuantity,totalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.product_name);
            price=itemView.findViewById(R.id.product_price);
            date=itemView.findViewById(R.id.current_date);
            time=itemView.findViewById(R.id.current_time);
            totalQuantity=itemView.findViewById(R.id.total_quantity);
            totalPrice=itemView.findViewById(R.id.total_price);
        }
    }
}
