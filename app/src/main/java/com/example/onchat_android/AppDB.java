package com.example.onchat_android;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.onchat_android.entities.Contact;
import com.example.onchat_android.entities.Message;

@Database(entities = {Message.class, Contact.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase{
    public abstract MessageDao messageDao();
    public abstract ContactDao contactDao();
}
