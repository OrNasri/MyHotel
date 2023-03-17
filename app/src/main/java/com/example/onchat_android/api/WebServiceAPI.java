package com.example.onchat_android.api;

import retrofit2.Call;

import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;
import com.example.onchat_android.entities.User;
import com.example.onchat_android.entities.newContact;
import com.example.onchat_android.entities.newContactInvitation;
import com.example.onchat_android.entities.newMessage;
import com.example.onchat_android.entities.newMessageTransfer;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {

 @GET("contacts")
 Call<List<User>> getUsers();

 @POST("contacts")
 Call<Void> addUser(@Body User user);

 @GET("contacts/{user}")
 Call<List<Contact>> getUserContacts(@Path("user") String id);

 @GET("contacts/{user1}/contacts/{user2}/messages")
 Call<List<Message>> getContactMessages(@Path("user1") String id1, @Path("user2") String id2);

 @GET("contacts/{user1}/contacts/{user2}")
 Call<Contact> getContact(@Path("user1") String id1, @Path("user2") String id2);

 @POST("contacts/{user1}/contacts/{user2}/messages")
 Call <Void> addMessage (@Path("user1") String id1, @Path("user2") String id2, @Body newMessage message);

 @POST("contacts/{user}/contacts")
 Call<Void> addContact(@Path("user") String id, @Body newContact contact);

 @POST("invitations")
 Call<Void> addContactWithInvitation(@Body newContactInvitation contact);

 @POST("transfer/{id}/{token}")
 Call<Void> addToken(@Path("id") String id, @Path("token") String token);

 @POST("transfer")
 Call<Void> addMessageWithTransfer(@Body newMessageTransfer message);

// @POST("posts")
// Call<Void> createPost(@Body Post post);
//
// @DELETE("posts/{id}")
// Call<Void> deletePost(@Path("id") int id);
}