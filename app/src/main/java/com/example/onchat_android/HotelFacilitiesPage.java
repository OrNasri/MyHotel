package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.onchat_android.api.PostAPI;

import java.util.ArrayList;
import java.util.List;

public class HotelFacilitiesPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_facilities_page);


        Button buttonBack = findViewById(R.id.backToMenuFacilities);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            startActivity(i);
        });

        List<String> facilities = new ArrayList<>();
        facilities.add("gym");
        facilities.add("Self");
        facilities.add("bar");
        facilities.add("Luggage");


        ListView lvProgram = findViewById(R.id.lvProgramFacilities);

        List<String> contactsNamesList = new ArrayList<>();
        contactsNamesList.add("Gym");
        contactsNamesList.add("Self-service laundry");
        contactsNamesList.add("Bar");
        contactsNamesList.add("Luggage room at Front desk");

        List<String> contactsMessagesList = new ArrayList<>();
        contactsMessagesList.add("Daily: 06:00 - 22:00");
        contactsMessagesList.add("Daily: 06:00 - 22:00");
        contactsMessagesList.add("Daily: 07:00 - 24:00");
        contactsMessagesList.add("Daily: 24/7");


        List<Integer> contactsImagesList = new ArrayList<>();
        contactsImagesList.add(R.drawable.gym);
        contactsImagesList.add(R.drawable.laundry);
        contactsImagesList.add(R.drawable.bar);
        contactsImagesList.add(R.drawable.luggage);


        FacilitiesAdapter adapter = new FacilitiesAdapter(this, contactsNamesList,
                contactsMessagesList, contactsImagesList);

        lvProgram.setAdapter(adapter);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        showFacilities();
//    }
}