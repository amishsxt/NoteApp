package com.example.db_app.Views;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_app.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tittle,content;
    ImageView delete;

    LinearLayout note;
    public MyViewHolder(@Nullable View itemView){
        super(itemView);

        tittle = itemView.findViewById(R.id.tittle);
        content = itemView.findViewById(R.id.content);

        delete = itemView.findViewById(R.id.delete_btn);
        note = itemView.findViewById(R.id.note_id);


    }
}
