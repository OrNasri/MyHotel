package com.example.onchat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;

import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.api.WebServiceAPI;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;
import com.example.onchat_android.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactPage extends AppCompatActivity {
    private PostAPI postAPI;
    private AppDB db;
    private MessageDao messageDao;
    List<Message> messages;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private List<User> users;
    private ListView listviewInAddContact;
    private ArrayAdapter<Contact> adapterAddContact;
    Intent intentFromChat;

    String current;
    private ArrayAdapter<User> adapter;

    public void getMessages(String current, String contact, Intent chat) {
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<List<Message>> call = webServiceAPI.getContactMessages(current, contact);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                messages = response.body();
                int size = messageDao.index().size();
                for (int j = 0; j < size; j++) {
                    messageDao.delete(messageDao.index().get(0));
                }
                for (int i = 0; i < messages.size(); i++) {
                    messageDao.insert(messages.get(i));
                }
                startActivity(chat);
            }

            @Override
            public void onFailure(@NonNull Call<List<Message>> call, Throwable t) {
            }
        });


    }

    public void showContacts() {
        List<String> contactsIdList = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactsIdList.add(contacts.get(i).id);
        }
        ListView lvProgram = findViewById(R.id.lvProgram);
        List<String> contactsNamesList = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactsNamesList.add(contacts.get(i).name);
        }
        List<String> contactsMessagesList = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactsMessagesList.add(contacts.get(i).last);
        }
        List<String> contactsTimeList = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactsTimeList.add(contacts.get(i).lastdate);
        }
        List<Integer> contactsImagesList = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            contactsImagesList.add(R.drawable.avatar);
        }

        ContactAdapter contactAdapter = new ContactAdapter(this, contactsNamesList,
                contactsMessagesList, contactsTimeList, contactsImagesList);

        lvProgram.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent chat = new Intent(ContactPage.this, chatScreen.class);
                chat.putExtra("contactInfo", contactsIdList.get(position));
                chat.putExtra("userName", current);
                getMessages(current, contactsIdList.get(position), chat);

            }
        });

        lvProgram.setAdapter(contactAdapter);

    }

    public void getContacts(String id) {
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<List<Contact>> call = webServiceAPI.getUserContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                contacts = response.body();
                for(Contact contact : contacts) {
                    String [] serverPath = contact.getServer().split(":");
                    contact.setServer("10.0.2.2:" + serverPath[1]);
                }
                int size = contactDao.index().size();
                for (int j = 0; j < size; j++) {
                    contactDao.delete(contactDao.index().get(0));
                }
                for (int i = 0; i < contacts.size(); i++) {
                    contactDao.insert(contacts.get(i));
                }

                showContacts();
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, Throwable t) {
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);
        current = getIntent().getExtras().getString("activeUser");

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "messagesDB")
                .allowMainThreadQueries()
                .build();
        messageDao = db.messageDao();

        AppDB dbInLogin = Room.databaseBuilder(getApplicationContext(), AppDB.class, "contactsDB")
                .allowMainThreadQueries()
                .build();
        contactDao = dbInLogin.contactDao();
        contacts = contactDao.index();

        postAPI = new PostAPI();

        FloatingActionButton btnAddContact = findViewById(R.id.btnAddContact);
        btnAddContact.setOnClickListener(v -> {
            Intent i = new Intent(this, addContact.class);
            i.putExtra("activeUser", current);
            startActivity(i);
        });


        listviewInAddContact = findViewById(R.id.listviewInAddContact);
        adapterAddContact = new ArrayAdapter<>(ContactPage.this, android.R.layout.simple_list_item_1, contacts);
        listviewInAddContact.setAdapter(adapterAddContact);

    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactDao.index());
        adapterAddContact.notifyDataSetChanged();
        getContacts(current);
    }

}