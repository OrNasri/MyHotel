package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class LocationsPage extends AppCompatActivity {
    private List<String> bag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_locations_page);
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }

        FloatingActionButton buttonBack = findViewById(R.id.backToMenuLocations);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });
    }
}