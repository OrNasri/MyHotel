package com.example.onchat_android.entities;

import androidx.room.PrimaryKey;

import java.util.List;

public class Chat {
    @PrimaryKey(autoGenerate = true)
    public String username;
    public List<Message> messages;

    public Chat(String username, List<Message> messages) {
        this.username = username;
        this.messages = messages;
    }
    public Chat() {
    }

    public String getUsername() {
        return username;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
