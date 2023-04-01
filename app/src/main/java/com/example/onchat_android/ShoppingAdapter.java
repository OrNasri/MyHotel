package com.example.onchat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        ContactPageViewHolder holder = null;
        if(adapter == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter = layoutInflater.inflate(R.layout.activity_shopping_adapter,parent,false);
            holder = new ContactPageViewHolder(adapter);
            adapter.setTag(holder);
        }
        else {
            holder = (ContactPageViewHolder) adapter.getTag();
        }

        holder.itemName.setText(NamesList.get(position));


        return adapter;

    }


}