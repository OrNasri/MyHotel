package com.example.onchat_android.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.onchat_android.R;

import java.util.List;

public class FacilitiesAdapter extends ArrayAdapter<String> {
    Context context;
    List<Integer> images;
    List<String> names;
    List<String> description;

    public FacilitiesAdapter(@NonNull Context context, List<String> names,List<String> description, List<Integer> images ) {
        super(context, R.layout.adapter, R.id.textView1, names);
        this.context = context;
        this.names = names;
        this.images = images;
        this.description = description;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View adapter = convertView;
        FacilitiesHelper holder = null;
        if(adapter == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            adapter = layoutInflater.inflate(R.layout.adapter,parent,false);
            holder = new FacilitiesHelper(adapter);
            adapter.setTag(holder);
        }
        else {
            holder = (FacilitiesHelper) adapter.getTag();
        }
        holder.itemImage.setImageResource(images.get(position));
        holder.itemName.setText(names.get(position));
        holder.itemDesc.setText(description.get(position));


        return adapter;

    }
}
