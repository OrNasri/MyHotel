package com.example.onchat_android.api;
import android.icu.util.Measure;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.onchat_android.MyApplication;
import com.example.onchat_android.R;
import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;
import com.example.onchat_android.entities.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostAPI {
 Retrofit retrofit;
 WebServiceAPI webServiceAPI;
// List <Message> messagesListFromApi;
 List<User> users;
 List<Contact> contacts;
 private String url =MyApplication.getBaseUrl();
 public PostAPI() {
  retrofit = new Retrofit.Builder()
          .baseUrl(url)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
  webServiceAPI = retrofit.create(WebServiceAPI.class);
 }

 public void setUrl(String url) {
  this.url = "http://" + url + "/api/";
 }

 public WebServiceAPI getService() {
  return webServiceAPI;
 }

 public void get() {
  Call<List<User>> call = webServiceAPI.getUsers();
  call.enqueue(new Callback<List<User>>() {
   @Override
   public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
    users = response.body();
   }

   @Override
   public void onFailure(@NonNull Call<List<User>> call, Throwable t) {
   }
  });
 }

// public void getContacts(String id) {
//  Call<List<Contact>> call = webServiceAPI.getUserContacts(id);
//  call.enqueue(new Callback<List<Contact>>() {
//   @Override
//   public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
//    contacts = response.body();
//   }
//
//   @Override
//   public void onFailure(@NonNull Call<List<Contact>> call, Throwable t) {
//   }
//  });
// }

 public void post(User user) {
  Call<Void> call = webServiceAPI.addUser(user);
  call.enqueue(new Callback<Void>() {
   @Override
   public void onResponse(Call<Void> call, Response<Void> response) {

   }

   @Override
   public void onFailure(Call<Void> call, Throwable t) {

   }
  });
}

// public void getContactsInfo(String currentUser, String contactName) {
//  Call<List<Message>> call = webServiceAPI.getContactMessages(currentUser, contactName);
//  call.enqueue(new Callback<List<Message>>() {
//   @Override
//   public void onResponse(@NonNull Call<List<Message>> call, @NonNull Response<List<Message>> response) {
//    messagesListFromApi = response.body();
//
//    // showContacts();
//   }
//   @Override
//   public void onFailure(@NonNull Call<List<Message>> call, Throwable t) {
//   }
//  });
//
// }

  public List<User> getUsersList() {
  return users;
 }

// public List<Message> getMessageList() {
//  return messagesListFromApi;
// }
 public List<Contact> getContactsList() {
  return contacts;
 }
 }