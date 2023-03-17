package com.example.onchat_android.entities;

import androidx.room.PrimaryKey;

public class newMessageTransfer {
    @PrimaryKey(autoGenerate = true)
    public String from;
    public String to;
    public String content;

    public newMessageTransfer(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
    public newMessageTransfer() {

    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getContent() {
        return content;
    }
}
