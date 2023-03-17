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

public class RestaurantAdapter extends ArrayAdapter<String> {
    Context context;
    List<Integer> images;
    List<String> names;
    List<String> lastMessages;

    public RestaurantAdapter(@NonNull Context context, List<String> names, List<String> lastMessages, List<Integer> images ) {
        super(context, R.layout.activity_resturant_adapter, R.id.textView1, names);
        this.context = context;
        this.names = names;
        this.images = images;
        this.lastMessages = lastMessages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View adapter = convertView;
        ContactPageViewHolder holder = null;
        if(adapter == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter = layoutInflater.inflate(R.layout.activity_resturant_adapter,parent,false);
            holder = new ContactPageViewHolder(adapter);
            adapter.setTag(holder);
        }
        else {
            holder = (ContactPageViewHolder) adapter.getTag();
        }
        holder.itemImage.setImageResource(images.get(position));
        holder.itemName.setText(names.get(position));
        holder.itemLastMessage.setText(lastMessages.get(position));


        return adapter;

    }
}
