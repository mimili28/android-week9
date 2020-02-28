package com.mariailieva.firebasehello.storage;

import com.mariailieva.firebasehello.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteStorage {
    public static List<Note> list = new ArrayList<>();

    public static Note getNote(int index){
        return list.get(index);
    }

    public static List<Note> getList(){
        return list;
    }
}
