package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        FloatingActionButton btnAddContact = findViewById(R.id.btnChat);
        btnAddContact.setOnClickListener(v -> {
            Intent i = new Intent(this, HelpPage.class);
            startActivity(i);
        });

        Button buttonFacilities = findViewById(R.id.button5);
        buttonFacilities.setOnClickListener(v -> {
            Intent k = new Intent(MenuPage.this, HotelFacilitiesPage.class);
            startActivity(k);
        });

        Button buttonContactInfo= findViewById(R.id.button4);
        buttonContactInfo.setOnClickListener(v -> {
            Intent j = new Intent(MenuPage.this, ContactInfoPage.class);
            startActivity(j);
        });
        Button buttonWifi = findViewById(R.id.button3);
        buttonWifi.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, WifiPage.class);
            startActivity(i);
        });

        Button diningRoom = findViewById(R.id.button6);
        diningRoom.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, DiningRoomPage.class);
            startActivity(i);
        });

        Button restaurant = findViewById(R.id.button7);
        restaurant.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, RestaurantPage.class);
            startActivity(i);
        });

        Button Locations = findViewById(R.id.button8);
        Locations.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, LocationsPage.class);
            startActivity(i);
        });

//        Button locations = findViewById(R.id.button8);
//        locations.setOnClickListener(v -> {
//            Intent i = new Intent(MenuPage.this, Mapy.class);
//            startActivity(i);
//        });




    }
}