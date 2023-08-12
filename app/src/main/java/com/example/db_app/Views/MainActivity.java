package com.example.db_app.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.db_app.Data.AppDatabase;
import com.example.db_app.Data.Note;
import com.example.db_app.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting views
        next = findViewById(R.id.add_note);
        recyclerView = findViewById(R.id.recycler_view);

        //Adapter
        MyAdapter myAdapter = new MyAdapter(this
                ,new MyAdapter.OnItemClickListener(){
                    //Opening Note
                    @Override
                    public void onItemClick(int postion, Note note){
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("check",1);
                        intent.putExtra("postion",postion);
                        intent.putExtra("note",note);
                        startActivity(intent);
                    }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

        //Navigation to next activity (Pressed on Add Note icon)
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("check",0);
                startActivity(intent);

            }
        });

    }
}