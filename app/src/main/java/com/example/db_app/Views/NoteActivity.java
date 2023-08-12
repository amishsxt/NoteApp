package com.example.db_app.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.db_app.Data.AppDatabase;
import com.example.db_app.Data.Note;
import com.example.db_app.R;

public class NoteActivity extends AppCompatActivity {
    EditText tittle,content;

    private ImageView  back;
    private AppDatabase appDatabase;
    private Note note;
    private int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);

        appDatabase = AppDatabase.getDB(this);

        tittle = findViewById(R.id.tittle_edit_text);
        content = findViewById(R.id.content_edit_text);
        tittle.setText("");
        content.setText("");

        back =  findViewById(R.id.back_btn);

        //Checking intent
        check = getIntent().getIntExtra("check", 0);

        if(check==1) {
            note = (Note) getIntent().getSerializableExtra("note");

            tittle.setText(note.tittle);
            content.setText(note.content);
        }


        //Navigation back to First Activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takeAction();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        takeAction();
    }

    private void createNode(AppDatabase appDatabase){

        //Checking if any data field is empty

        if( tittle.getText().toString().trim().length() > 0 &&
                content.getText().toString().trim().length() > 0 )
        {
            //Insert Data
            appDatabase.noteDao().insertRecord(new Note(tittle.getText().toString(), content.getText().toString()));
        }
        else if( tittle.getText().toString().trim().length() > 0 &&
                content.getText().toString().trim().length() < 1 )
        {
            Note note = new Note();
            note.setTittle(tittle.getText().toString());
            appDatabase.noteDao().insertRecord(note);
        }
        else if (tittle.getText().toString().trim().length() < 1 &&
                content.getText().toString().trim().length() > 0)
        {
            Note note = new Note();
            note.setContent(content.getText().toString());
            appDatabase.noteDao().insertRecord(note);
        }

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private void deleteNote(Note note, AppDatabase appDatabase){

        //Deleting data
        appDatabase.noteDao().deleteRecord(new Note(note.getId(), note.getTittle(), note.getContent()));

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void updateNode(Note note, AppDatabase appDatabase){

        //Updating data
        appDatabase.noteDao().updateRecord(new Note(note.getId(), tittle.getText().toString(), content.getText().toString()));

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void takeAction(){
        if(check==1){
            note = (Note) getIntent().getSerializableExtra("note");
            //Checking if empty
            if(tittle.getText().toString().trim().length() > 0 ||
                    content.getText().toString().trim().length() > 0 ){

                updateNode(note,appDatabase);
            }
            else {

                deleteNote(note,appDatabase);
            }
        }
        else {

            createNode(appDatabase);
        }
    }
}