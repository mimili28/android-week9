package com.mariailieva.firebasehello;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.mariailieva.firebasehello.adapter.NoteRecyclerViewAdapter;
import com.mariailieva.firebasehello.model.Note;
import com.mariailieva.firebasehello.storage.NoteStorage;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    NoteRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter=new NoteRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL));

        startNoteListener();

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewNote();
            }
        });
    }




    private void startNoteListener() {
        db.collection(notes).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                NoteStorage.list.clear();
                for (DocumentSnapshot snap: values.getDocuments()){
                    Log.i("all", "read from FB " + snap.get("head").toString() + " " + snap.get("body").toString());
                    NoteStorage.list.add(new Note(snap.getId(), snap.get("head").toString(), snap.get("body").toString()));
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void addNewNote() {
        DocumentReference docRef = db.collection(notes).document();
        Map<String, String> map = new HashMap<>();
        map.put("head","new note");
        map.put("body","body");
        docRef.set(map);
    }
}
