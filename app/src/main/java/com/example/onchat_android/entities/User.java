package com.example.onchat_android.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User
{
    // id = username
    @PrimaryKey
    @NonNull
    public String id;
    //[Required]
    public String nickname;

    // [Required]

    public String password;
    //[Required]
    public String image;

    //[Required]
  //  public List<Contact> contacts;
    //[Required]
   // public List<Chat> chats;

    public User() {

    }

    public User(String id, String nickName, String password) {
        this.id = id;
        this.nickname = nickName;
        this.password = password;
        this.image = "https://bootdey.com/img/Content/avatar/avatar4.png";
      //  this.contacts = new ArrayList<>();
      //  this.chats = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id" + id +
                ", nickName='" + nickname + '\'' +
                '}';
    }
    //public List<Contact> getContacts() {
       // return contacts;
    //}

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImage(String image) {
        this.image = image;
    }

   // public void setContacts(List<Contact> contacts) {
    //    this.contacts = contacts;
   // }

   // public void setChats(List<Chat> chats) {
   //     this.chats = chats;
   // }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    //public List<Chat> getChats() {
    //    return chats;
   // }

    public void setId(String id) {
        this.id = id;
    }

}