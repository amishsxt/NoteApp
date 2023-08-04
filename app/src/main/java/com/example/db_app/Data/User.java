package com.example.db_app.Data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name="Tittle")
    public String tittle;

    @ColumnInfo(name="Content")
    public String content;
    @Ignore
    public User(int id, String tittle, String content) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
    }
    public User(String tittle, String content) {
        this.tittle = tittle;
        this.content = content;
    }
    public User(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
