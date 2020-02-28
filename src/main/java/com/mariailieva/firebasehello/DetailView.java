package com.mariailieva.firebasehello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mariailieva.firebasehello.storage.NoteStorage;

import java.util.HashMap;
import java.util.Map;

public class DetailView extends AppCompatActivity {

    private EditText headline;
    private EditText body;
    private int position;

    private final static String notes = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        headline=findViewById(R.id.editTextHeadline);
        body = findViewById(R.id.editTextBody);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        headline.setText(NoteStorage.getNote(position).getHeadline());
        body.setText(NoteStorage.getNote(position).getBody());
    }

    @Override
    protected void onPause(){
        super.onPause();
        NoteStorage.getNote(position).setHeadline(headline.getText().toString());
        NoteStorage.getNote(position).setBody(body.getText().toString());
        editNote(NoteStorage.getNote(position).getId());
    }

    private void editNote(String id) {
        DocumentReference docRef = db.collection(notes).document(id);
        Map<String, String> map = new HashMap<>();
        map.put("head",NoteStorage.getNote(position).getHeadline());
        map.put("body",NoteStorage.getNote(position).getBody());
        docRef.set(map);
    }


}
