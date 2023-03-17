package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.entities.User;

import java.util.List;

public class SignUpPage extends AppCompatActivity {
    private AppDB db;
   // private UserDao userDao;
    private PostAPI postAPI;
    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        postAPI= new PostAPI();
        postAPI.get();

//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "UsersDB")
//                .allowMainThreadQueries()
//                .build();
//
//        userDao = db.userDao();

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(v -> {
            EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
            EditText editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
            EditText editTextTextPassword = findViewById(R.id.editTextTextPassword);
            EditText editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
            String name = editTextTextPersonName.getText().toString();
            String nickname = editTextTextPersonName2.getText().toString();
            String password = editTextTextPassword.getText().toString();
            String confirmPass = editTextTextPassword2.getText().toString();
            int flag = 0;
            if(name.isEmpty() || nickname.isEmpty() || password.isEmpty() || confirmPass.isEmpty()){
                flag = 1;
                Toast.makeText(SignUpPage.this, "There is an empty field!", Toast.LENGTH_SHORT).show();
            }

            else if (password.compareTo(confirmPass) != 0) {
                flag = 1;
                Toast.makeText(SignUpPage.this, "The passwords do not match!", Toast.LENGTH_SHORT).show();

            }
            else if (! (password.matches(".*[0-9].*")) || ! (password.matches(".*[A-Z].*")) || (password.length() < 8)){
                flag = 1;
                Toast.makeText(SignUpPage.this, "The password must contain at least 8 characters with an uppercase letter and numbers !", Toast.LENGTH_SHORT).show();
            }
            List<User> usersList = postAPI.getUsersList();
            for(int i=0; i< usersList.size(); i++){
                if(usersList.get(i).id.equals(name)){
                    Toast.makeText(SignUpPage.this, "Username is already exist!", Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                User user = new User(editTextTextPersonName.getText().toString(),
                        editTextTextPersonName2.getText().toString(),
                        editTextTextPassword.getText().toString());
                postAPI.post(user);
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }
            else {
                Intent j = new Intent(this, SignUpPage.class);
                startActivity(j);
            }
        });

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });
    }
}