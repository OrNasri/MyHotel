package com.example.onchat_android.restaurant;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.onchat_android.R;

import java.util.List;

public class ShoppingAdapter extends ArrayAdapter<String> {
    Context context;
    List<String> NamesList;

    public ShoppingAdapter(@NonNull Context context, List<String> list) {
        super(context, R.layout.activity_shopping_adapter, R.id.textView1, list);
        this.context = context;
        this.NamesList = list;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View adapter = convertView;
        FacilitiesHelper holder = null;
        if(adapter == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter = layoutInflater.inflate(R.layout.activity_shopping_adapter,parent,false);
            holder = new FacilitiesHelper(adapter);
            adapter.setTag(holder);
        }
        else {
            holder = (FacilitiesHelper) adapter.getTag();
        }

        holder.itemName.setText(NamesList.get(position));
        return adapter;
    }
}