package com.example.db_app.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String db_name = "NotesDataBase";
    private static AppDatabase instance;

    public static synchronized AppDatabase getDB(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context, AppDatabase.class, db_name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
    public abstract NoteDao noteDao();

}
