package com.example.onchat_android.entities;

import androidx.room.PrimaryKey;

public class newContact {
    @PrimaryKey(autoGenerate = true)
    public String id;
    public String name;
    public String server;

    public newContact(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }
    public newContact() {

    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getServer() {
        return server;
    }
}
