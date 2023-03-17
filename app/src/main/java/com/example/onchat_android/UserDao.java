package com.example.onchat_android;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onchat_android.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> index();

   // @Query("SELECT * FROM user WHERE id = :id")
    // User get(String id);

    @Insert
    void insert (User... users);


}
