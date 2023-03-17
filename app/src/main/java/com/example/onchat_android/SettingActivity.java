package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        FloatingActionButton buttonChangeServer = findViewById(R.id.buttonChangeServer);
        buttonChangeServer.setOnClickListener(v -> {
            EditText editTextTextServer = findViewById(R.id.editTextTextServer);
            if (!editTextTextServer.getText().toString().isEmpty()) {
                MyApplication.setBaseUrl("http://" + editTextTextServer.getText().toString() + "/api/");
            }
            finish();
        });
    }
}