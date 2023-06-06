package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.onchat_android.chat.MyAgent;
import com.example.onchat_android.restaurant.RestaurantPage;

import java.util.ArrayList;
import java.util.List;

public class MenuPage extends AppCompatActivity {
    private List<String> bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_page);
        getSupportActionBar().hide();
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }


        Button buttonFacilities = findViewById(R.id.button5);
        buttonFacilities.setOnClickListener(v -> {
            Intent k = new Intent(MenuPage.this, HotelFacilitiesPage.class);
            k.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(k);
        });

        Button buttonContactInfo= findViewById(R.id.button4);
        buttonContactInfo.setOnClickListener(v -> {
            Intent j = new Intent(MenuPage.this, ContactInfoPage.class);
            j.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(j);
        });
        Button buttonWifi = findViewById(R.id.button3);
        buttonWifi.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, WifiPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        Button diningRoom = findViewById(R.id.button6);
        diningRoom.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, DiningRoomPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        Button restaurant = findViewById(R.id.button7);
        restaurant.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, RestaurantPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        Button Locations = findViewById(R.id.button8);
        Locations.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, LocationsPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        Button agent = findViewById(R.id.button9);
        agent.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, MyAgent.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        Button emergency = findViewById(R.id.button10);
        emergency.setOnClickListener(v -> {
            Intent i = new Intent(MenuPage.this, Emergency.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });
    }
}