package com.example.db_app.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("select *from Note order by id desc")
    List<Note> getAllRecords();
    @Insert
    void insertRecord(Note note);
    @Delete
    void deleteRecord(Note note);

    @Update
    void updateRecord(Note note);
}
