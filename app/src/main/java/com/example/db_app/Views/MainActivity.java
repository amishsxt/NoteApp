package com.example.db_app.Views;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.db_app.Data.User;
import com.example.db_app.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Adapter
        MyAdapter myAdapter = new MyAdapter(this
                ,new MyAdapter.OnItemClickListener(){
                    //Opening Note
                    @Override
                    public void onItemClick(int postion, User user){
                        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                        intent.putExtra("check",1);
                        intent.putExtra("postion",postion);
                        intent.putExtra("user",user);
                        startActivity(intent);
                    }
        });

        recyclerView.setAdapter(myAdapter);

        //Navigation to next activity (Pressed on Add Note icon)
        ImageView next = findViewById(R.id.add_note);
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