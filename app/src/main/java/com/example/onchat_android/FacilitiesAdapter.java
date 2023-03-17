package com.example.onchat_android;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class FacilitiesAdapter extends ArrayAdapter<String> {
    Context context;
    List<Integer> images;
    List<String> names;
    List<String> lastMessages;

    public FacilitiesAdapter(@NonNull Context context, List<String> names,List<String> lastMessages, List<Integer> images ) {
        super(context, R.layout.adapter, R.id.textView1, names);
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
            adapter = layoutInflater.inflate(R.layout.adapter,parent,false);
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
