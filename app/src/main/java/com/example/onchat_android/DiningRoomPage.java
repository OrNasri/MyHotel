package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DiningRoomPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_room_page);


        Button buttonBack = findViewById(R.id.backToMenuDining);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
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