package com.example.onchat_android;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactPageViewHolder {
    ImageView itemImage;
    TextView itemName;
    TextView itemLastMessage;
    TextView itemTime;
    ContactPageViewHolder(View v) {
        itemImage = v.findViewById(R.id.imageView3);
        itemName = v.findViewById(R.id.textView1);
        itemLastMessage = v.findViewById(R.id.textView2);
        itemTime = v.findViewById(R.id.textView6);
    }
}
