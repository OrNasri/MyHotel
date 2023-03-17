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

public class ContactAdapter extends ArrayAdapter<String> {
    Context context;
    List<Integer> images;
    List<String> names;
    List<String> lastMessages;
    List<String> times;
    public ContactAdapter(@NonNull Context context, List<String> names,List<String> lastMessages, List<String> times, List<Integer> images ) {
        super(context, R.layout.single_contact, R.id.textView1, names);
        this.context = context;
        this.names = names;
        this.images = images;
        this.lastMessages = lastMessages;
        this.times = times;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View singleContact = convertView;
        ContactPageViewHolder holder = null;
        if(singleContact == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleContact = layoutInflater.inflate(R.layout.single_contact,parent,false);
            holder = new ContactPageViewHolder(singleContact);
            singleContact.setTag(holder);
        }
        else {
            holder = (ContactPageViewHolder) singleContact.getTag();
        }
        holder.itemImage.setImageResource(images.get(position));
        holder.itemName.setText(names.get(position));
        holder.itemLastMessage.setText(lastMessages.get(position));
        holder.itemTime.setText(times.get(position));

        return singleContact;

    }
}
