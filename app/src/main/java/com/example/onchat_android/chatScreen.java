package com.example.onchat_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.onchat_android.api.ContactsAPI;
import com.example.onchat_android.api.PostAPI;
import com.example.onchat_android.api.WebServiceAPI;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;
import com.example.onchat_android.entities.User;
import com.example.onchat_android.entities.newMessage;
import com.example.onchat_android.entities.newMessageTransfer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chatScreen extends AppCompatActivity {
    private List<Message> messagesList;
    private Contact contact;
    private  PostAPI postAPI;
    private newMessage newmessage;
    private ArrayAdapter<Message> adapter;
    private MessageDao messageDao;
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ListView listview;
    private String contactName;
    private String currentUser;


    public void updateOtherContact(String server) {
        ContactsAPI contactsAPI = new ContactsAPI("10.0.2.2:" + server);
        WebServiceAPI contactWebService = contactsAPI.getWebServiceAPI();
        newMessageTransfer newTransfer = new newMessageTransfer(currentUser, contactName, newmessage.getContent());
        Call<Void> call = contactWebService.addMessageWithTransfer(newTransfer);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
            }
            @Override
            public void onFailure(@NonNull Call<Void> call, Throwable t) {
            }
        });
    }

    public void func() {
        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        mMessageAdapter = new MessageListAdapter(this, messagesList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        EditText edit_gchat_message = findViewById(R.id.edit_gchat_message);
        edit_gchat_message.setText("");
        if( newmessage!= null) {
            String[] server = contact.getServer().split(":");
            contact.setServer("10.0.2.2:" + server[1]);
            updateOtherContact(server[1]);
        }
    }

    public void getContactsInfo(String currentUser, String contactName) {
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<List<Message>> call = webServiceAPI.getContactMessages(currentUser, contactName);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
                messagesList = response.body();
                func();

                // showContacts();
            }
            @Override
            public void onFailure(@NonNull Call<List<Message>> call, Throwable t) {
            }
        });

    }

    public void getContact(String currentUser, String contactName){
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<Contact> call = webServiceAPI.getContact(currentUser, contactName);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NonNull Call<Contact> call, @NonNull Response<Contact> response) {
                contact = response.body();
                TextView currentUserName = (TextView)findViewById(R.id.currentUserName);
                currentUserName.setText(contact.name);
                ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
                imageView2.setImageResource(R.drawable.avatar);
                getContactsInfo(currentUser,contactName);
            }
            @Override
            public void onFailure(@NonNull Call<Contact> call, Throwable t) {
            }
        });
    }


    public void setToken (String token) {
        WebServiceAPI webServiceAPI = postAPI.getService();
        Call<Void> call = webServiceAPI.addToken(currentUser,token);
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
        contactName = getIntent().getExtras().getString("contactInfo");
        currentUser = getIntent().getExtras().getString("userName");

        AppDB db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "messagesDB")
                .allowMainThreadQueries()
                .build();
        messageDao = db.messageDao();

        messagesList = messageDao.index();
        setContentView(R.layout.activity_chat_screen);
        postAPI = new PostAPI();

//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "messagesDB")
//                .allowMainThreadQueries()
//                .build();
//        messageDao = db.messageDao();

//        Button backToContact = findViewById(R.id.backToContact);
//        backToContact.setOnClickListener(i -> {
//            Intent j = new Intent(chatScreen.this, ContactPage.class);
//            startActivity(j);
//        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(chatScreen.this,
                new OnSuccessListener<InstanceIdResult>(){
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        String newToken = instanceIdResult.getToken();
                        setToken(newToken);
                    }
                });

        FloatingActionButton button_gchat_send = findViewById(R.id.button_gchat_send);
        button_gchat_send.setOnClickListener(v -> {
            EditText edit_gchat_message = findViewById(R.id.edit_gchat_message);
            newmessage = new newMessage(edit_gchat_message.getText().toString());
            WebServiceAPI webServiceAPI = postAPI.getService();
            Call<Void> call = webServiceAPI.addMessage(currentUser, contactName,newmessage);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    Message insertMessage = new Message("100", newmessage.getContent(), "10:00", true);
                    messagesList.add(insertMessage);
               //     List<Message> temp = messageDao.index();
                    int size =messageDao.index().size();
                    for(int j = 0; j < size; j ++) {
                        messageDao.delete(messageDao.index().get(0));
                    }
                    for(int i=0; i< messagesList.size(); i++){
                      messageDao.insert(messagesList.get(i));
                    }
                   // messageDao.update(insertMessage);
                    onResume();
                    // setContentView(R.layout.activity_chat_screen);
                    // getContact(currentUser, contactName);
                }
                @Override
                public void onFailure(@NonNull Call<Void> call, Throwable t) {
                }
            });

        });



        listview = findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(chatScreen.this, android.R.layout.simple_list_item_1,messagesList);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        messagesList.clear();
        messagesList.addAll(messageDao.index());
        adapter.notifyDataSetChanged();
        getContact(currentUser, contactName);
    }
}