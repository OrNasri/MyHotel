package com.example.onchat_android.restaurant;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.onchat_android.R;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<Product> {
    Context context;
    List<Product> productList;

    public RestaurantAdapter(@NonNull Context context, List<Product> list) {
        super(context, R.layout.activity_resturant_adapter, R.id.textView1, list);
        this.context = context;
        this.productList = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View adapter = convertView;
        FacilitiesHelper holder = null;
        if(adapter == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter = layoutInflater.inflate(R.layout.activity_resturant_adapter,parent,false);
            holder = new FacilitiesHelper(adapter);
            adapter.setTag(holder);
        }
        else {
            holder = (FacilitiesHelper) adapter.getTag();
        }
        holder.itemImage.setImageResource(productList.get(position).getImg());
        holder.itemName.setText(productList.get(position).getTitleP());
        holder.itemDesc.setText(productList.get(position).getDesc());
        return adapter;
    }
}
