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


import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.api.WebServiceAPI;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppDB dbInLogin;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ArrayAdapter<User> adapter;
    private  PostAPI postAPI;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword2;
    int flag = 0;

    public void getContactList(Intent j, String userId){
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<List<Contact>> call = webServiceAPI.getUserContacts(userId);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                contacts = response.body();
                int size =contactDao.index().size();
                for(int j = 0; j < size; j ++) {
                    contactDao.delete(contactDao.index().get(0));
                }
                for(int i=0; i< contacts.size(); i++){
                    contactDao.insert(contacts.get(i));

                }
                startActivity(j);
            }
            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, Throwable t) {
            }
        });
    }

    public void setToken (String token) {
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<Void> call = webServiceAPI.addToken(editTextTextPersonName.getText().toString(),token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {

            }
            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);



        dbInLogin = Room.databaseBuilder(getApplicationContext(), AppDB.class, "contactsDB")
                .allowMainThreadQueries()
                .build();
        contactDao = dbInLogin.contactDao();

        postAPI= new PostAPI();

//        Button buttonSettings = findViewById(R.id.buttonSettings);
//        buttonSettings.setOnClickListener(v -> {
//            Intent j = new Intent(MainActivity.this, SettingActivity.class);
//            startActivity(j);
//        });
        postAPI.get();

//        Button buttonSignUp = findViewById(R.id.buttonSignUp);
//        buttonSignUp.setOnClickListener(v -> {
//            Intent i = new Intent(this, SignUpPage.class);
//            startActivity(i);
//        });
//
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this,
//                new OnSuccessListener<InstanceIdResult>(){
//            @Override
//            public void onSuccess(InstanceIdResult instanceIdResult) {
//                String newToken = instanceIdResult.getToken();
//                setToken(newToken);
//            }
//        });


        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(v -> {
            editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
            editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
            List<User> usersList = postAPI.getUsersList();
            //  userDao.get(editTextTextPersonName.getText().toString());
            for(int i=0; i< usersList.size(); i++){
                if(usersList.get(i).id.equals(editTextTextPersonName.getText().toString())){
                    if(usersList.get(i).password.equals(editTextTextPassword2.getText().toString())){
                        flag = 1;
                        break;
                    }
                }
            }
            if(flag == 1) {
                flag = 0;
                Intent j = new Intent(this, MenuPage.class);
                //j.putExtra("activeUser",editTextTextPersonName.getText().toString());
               // getContactList(j, editTextTextPersonName.getText().toString());
                startActivity(j);

            }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Username or Password is incorrect");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            //finish();

        });

    }
}