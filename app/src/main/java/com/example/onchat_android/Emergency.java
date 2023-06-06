package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Emergency extends AppCompatActivity {
    private List<String> bag;
    List<String> whoSend;
    List<String> mesInStrings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_emergency);
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }
        mesInStrings = getIntent().getStringArrayListExtra("messages");
        whoSend = getIntent().getStringArrayListExtra("sender");

        FloatingActionButton buttonBack = findViewById(R.id.backToMenuEmergency);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            i.putStringArrayListExtra("messages", (ArrayList<String>) mesInStrings);
            i.putStringArrayListExtra("sender", (ArrayList<String>) whoSend);
            startActivity(i);
        });
    }
}