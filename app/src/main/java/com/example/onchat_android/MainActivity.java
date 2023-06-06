package com.example.onchat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.api.WebServiceAPI;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button buttonLogin;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword2;
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> {
            editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
            editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);

            String mail_txt = editTextTextPersonName.getText().toString();
            String pass_txt = editTextTextPassword2.getText().toString();
            if(!mail_txt.equals("") && !pass_txt.equals("")){
                login(mail_txt, pass_txt);
            }
            else {
                Toast toast = Toast.makeText(MainActivity.this, "One or two fields are empty", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    private void login(String mail, String pass) {
        auth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent j = new Intent(MainActivity.this, MenuPage.class);
                startActivity(j);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                    Toast toast = Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    System.out.println(e.getMessage());
                }
            });

    }
}