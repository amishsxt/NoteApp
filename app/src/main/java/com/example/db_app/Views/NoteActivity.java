package com.example.db_app.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.db_app.Data.AppDatabase;
import com.example.db_app.Data.User;
import com.example.db_app.R;

public class NoteActivity extends AppCompatActivity {
    EditText tittle,content;

    ImageView  back;
    AppDatabase appDatabase;
    User user;

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
        int check = getIntent().getIntExtra("check", 0);

        if(check==1) {
            user = (User) getIntent().getSerializableExtra("user");

            tittle.setText(user.tittle);
            content.setText(user.content);
        }

        //Navigation back to First Activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(check==1){
                    user = (User) getIntent().getSerializableExtra("user");
                    //Checking if empty
                    if(tittle.getText().toString().trim().length() > 0 ||
                            content.getText().toString().trim().length() > 0 ){

                        UpdateNode(user,appDatabase);
                    }
                    else {

                        DeleteNote(user,appDatabase);
                    }
                }
                else {

                    CreateNode(appDatabase);
                }

            }
        });
    }

    private void CreateNode(AppDatabase appDatabase){

        //Checking if any data field is empty

        if( tittle.getText().toString().trim().length() > 0 &&
                content.getText().toString().trim().length() > 0 )
        {
            //Insert Data
            appDatabase.userDao().insertRecord(new User(tittle.getText().toString(), content.getText().toString()));
        }
        else if( tittle.getText().toString().trim().length() > 0 &&
                content.getText().toString().trim().length() < 1 )
        {
            User user = new User();
            user.setTittle(tittle.getText().toString());
            appDatabase.userDao().insertRecord(user);
        }
        else if (tittle.getText().toString().trim().length() < 1 &&
                content.getText().toString().trim().length() > 0)
        {
            User user = new User();
            user.setContent(content.getText().toString());
            appDatabase.userDao().insertRecord(user);
        }

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    private void DeleteNote(User user,AppDatabase appDatabase){

        //Deleting data
        appDatabase.userDao().deleteRecord(new User(user.getId(), user.getTittle(), user.getContent()));

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void UpdateNode(User user,AppDatabase appDatabase){

        //Updating data
        appDatabase.userDao().updateRecord(new User(user.getId(), tittle.getText().toString(), content.getText().toString()));

        Intent intent = new Intent(NoteActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}