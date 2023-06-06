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

public class HotelFacilitiesPage extends AppCompatActivity {
    private List<String> bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hotel_facilities_page);
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }


        FloatingActionButton buttonBack = findViewById(R.id.backToMenuFacilities);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
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
}