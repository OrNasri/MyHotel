package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.onchat_android.restaurant.FacilitiesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DiningRoomPage extends AppCompatActivity {
    private List<String> bag;
    List<String> whoSend;
    List<String> mesInStrings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dining_room_page);
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }
        mesInStrings = getIntent().getStringArrayListExtra("messages");
        whoSend = getIntent().getStringArrayListExtra("sender");


        FloatingActionButton buttonBack = findViewById(R.id.backToMenuDining);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            i.putStringArrayListExtra("messages", (ArrayList<String>) mesInStrings);
            i.putStringArrayListExtra("sender", (ArrayList<String>) whoSend);
            startActivity(i);
        });

        List<String> dining = new ArrayList<>();
        dining.add("breakfast");
        dining.add("lunch");
        dining.add("dinner");

        ListView lvProgram = findViewById(R.id.lvProgramDining);

        List<String> contactsNamesList = new ArrayList<>();
        contactsNamesList.add("Breakfast");
        contactsNamesList.add("Lunch");
        contactsNamesList.add("Dinner");

        List<String> contactsMessagesList = new ArrayList<>();
        contactsMessagesList.add("Daily: 07:00 - 10:30");
        contactsMessagesList.add("Daily: 12:00 - 14:30");
        contactsMessagesList.add("Daily: 19:00 - 22:00");



        List<Integer> contactsImagesList = new ArrayList<>();
        contactsImagesList.add(R.drawable.breakfast);
        contactsImagesList.add(R.drawable.lunch);
        contactsImagesList.add(R.drawable.dinner);

        FacilitiesAdapter adapter = new FacilitiesAdapter(this, contactsNamesList,
                contactsMessagesList, contactsImagesList);

        lvProgram.setAdapter(adapter);

    }
}