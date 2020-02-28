package com.mariailieva.firebasehello.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariailieva.firebasehello.storage.NoteStorage;
import com.mariailieva.firebasehello.R;
import com.mariailieva.firebasehello.view.ViewHolder;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.
                from(parent.getContext()).inflate(R.layout.noterow, parent, false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        holder.setPosition(position);
    }

    @Override
    public int getItemCount() {
        return NoteStorage.getList().size();
    }

}
