package com.example.onchat_android.entities;

import androidx.room.PrimaryKey;

public class newContactInvitation {
    @PrimaryKey(autoGenerate = true)
    public String from;
    public String to;
    public String server;

    public newContactInvitation(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
    public newContactInvitation() {

    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getServer() {
        return server;
    }
}
