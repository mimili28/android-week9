package com.mariailieva.firebasehello.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mariailieva.firebasehello.DetailView;
import com.mariailieva.firebasehello.R;
import com.mariailieva.firebasehello.storage.NoteStorage;


public class ViewHolder extends RecyclerView.ViewHolder  {

    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView textView;
    private Context context;
    int rowNumber = 0;

    public ViewHolder(@NonNull View itemView){
        super(itemView);
        LinearLayout linearLayout=(LinearLayout) itemView;
        context = itemView.getContext();
        textView = linearLayout.findViewById(R.id.textView);
        openDetailView();
        deleteOnLongClick();
    }

    private void deleteOnLongClick() {
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                deleteNote(NoteStorage.getNote(rowNumber).getId());
                return false;
            }
        });
    }

    private void openDetailView() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailView.class);
                intent.putExtra("position",rowNumber);
                context.startActivity(intent);
            }
        });
    }

    public void setPosition(int position) {
        rowNumber=position;
        textView.setText(NoteStorage.getNote(position).getHeadline());
    }

    private void deleteNote(String id) {
        DocumentReference docRef = db.collection(notes).document(id);
        docRef.delete();
    }
}
