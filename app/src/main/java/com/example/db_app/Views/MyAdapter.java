package com.example.db_app.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_app.Data.AppDatabase;
import com.example.db_app.Data.Note;
import com.example.db_app.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private ArrayList<Note> items;
    private AppDatabase appDatabase;

    private OnItemClickListener listener;
    public MyAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.appDatabase = AppDatabase.getDB(context);
        showNotes();
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Note note);
    }

    public void showNotes(){
        items = (ArrayList<Note>) appDatabase.noteDao().getAllRecords();
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tittle.setText(items.get(position).getTittle());
        holder.content.setText(items.get(position).content);

        //Deleting Data
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                appDatabase.noteDao().deleteRecord(new Note(items.get(position).getId()
                        ,items.get(position).getTittle()
                ,items.get(position).getContent()));

                showNotes();
                notifyDataSetChanged();
            }
        });

        //Opening note
        holder.note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(position, new Note(items.get(position).getId()
                        ,items.get(position).getTittle()
                        ,items.get(position).getContent()));
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

}

