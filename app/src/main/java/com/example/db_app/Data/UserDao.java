package com.example.db_app.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.db_app.Data.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select *from User order by id desc")
    List<User> getAllRecords();
    @Insert
    void insertRecord(User users);
    @Delete
    void deleteRecord(User user);

    @Update
    void updateRecord(User user);
}
