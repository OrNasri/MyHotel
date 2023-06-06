package com.example.onchat_android.restaurant;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onchat_android.R;

public class FacilitiesHelper {
    ImageView itemImage;
    TextView itemName;
    TextView itemDesc;
    TextView itemTime;
    FacilitiesHelper(View v) {
        itemImage = v.findViewById(R.id.imageView3);
        itemName = v.findViewById(R.id.textView1);
        itemDesc = v.findViewById(R.id.textView2);
        itemTime = v.findViewById(R.id.textView6);
    }
}
