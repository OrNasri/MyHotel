package com.example.onchat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.onchat_android.api.ContactsAPI;
import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.api.WebServiceAPI;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;
import com.example.onchat_android.entities.User;
import com.example.onchat_android.entities.newContact;
import com.example.onchat_android.entities.newContactInvitation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addContact extends AppCompatActivity {
    private PostAPI postAPI;
    EditText editTextTextPersonName4; //id
    EditText editTextTextPersonName5; //server
    EditText editTextTextPersonName6; //nickname
    String currentUser;
    WebServiceAPI webServiceAPI;
    private ContactDao contactDao;
    private List<Contact> contacts;

    public void addToOther(String server) {
        ContactsAPI contactsAPI = new ContactsAPI("10.0.2.2:" + server);
        WebServiceAPI contactWebService = contactsAPI.getWebServiceAPI();
        newContactInvitation addNew = new newContactInvitation(currentUser,
                editTextTextPersonName4.getText().toString(),editTextTextPersonName5.getText().toString());

        Call<Void> call = contactWebService.addContactWithInvitation(addNew);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                finish();
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        AppDB dbInLogin = Room.databaseBuilder(getApplicationContext(), AppDB.class, "contactsDB")
                .allowMainThreadQueries()
                .build();
        contactDao = dbInLogin.contactDao();
//        contacts = contactDao.index();

        postAPI= new PostAPI();
        postAPI.get();
        currentUser = getIntent().getExtras().getString("activeUser");

        FloatingActionButton buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonAddContact.setOnClickListener(v -> {
            webServiceAPI = postAPI.getService();
            editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
            editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
            editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);

            String id = editTextTextPersonName4.getText().toString();
            String server = editTextTextPersonName5.getText().toString();
            String nick = editTextTextPersonName6.getText().toString();

            int flag = 0;
            if(id.isEmpty() || server.isEmpty() || nick.isEmpty()){
                flag = 1;
                Toast.makeText(addContact.this, "There is an empty field!", Toast.LENGTH_SHORT).show();
            }
            else if(id.equals(currentUser)) {
                Toast.makeText(addContact.this, "You can not add yourself!", Toast.LENGTH_SHORT).show();
                flag = 1;
            }
            for(int i=0; i< contactDao.index().size(); i++){
                if(contactDao.index().get(i).id.equals(id)){
                    Toast.makeText(addContact.this, "You cannot add an existing contact!", Toast.LENGTH_SHORT).show();
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                newContact addNewContact = new newContact(editTextTextPersonName4.getText().toString(),
                        editTextTextPersonName6.getText().toString(), editTextTextPersonName5.getText().toString());
                Call<Void> call = webServiceAPI.addContact(currentUser, addNewContact);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        Contact newContact = new Contact(addNewContact.getId(), addNewContact.getName(),
                                addNewContact.getServer());
                        String[] server = newContact.getServer().split(":");
                        newContact.setServer("10.0.2.2:" + server[1]);
                        contactDao.insert(newContact);
                        addToOther(server[1]);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, Throwable t) {
                    }

                });
            }
        });

//        listviewInAddContact = findViewById(R.id.listview);
//        adapterAddContact = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);
//        listviewInAddContact.setAdapter(adapterAddContact);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        contacts.clear();
//        contacts.addAll(contactDao.index());
//        adapterAddContact.notifyDataSetChanged();
//    }
}