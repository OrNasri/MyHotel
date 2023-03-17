package com.example.onchat_android.entities;

import androidx.room.PrimaryKey;

public class newMessage {
    @PrimaryKey(autoGenerate = true)
    public String content = null;

    public String getContent() {
        return content;
    }

    public newMessage(String content) {
        this.content = content;
    }


}
