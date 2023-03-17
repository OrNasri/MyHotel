package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingBag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_bag);

        FloatingActionButton btnMenu = findViewById(R.id.menu);
        btnMenu.setOnClickListener(v -> {
            Intent i = new Intent(this, RestaurantPage.class);
            startActivity(i);
        });
    }
}