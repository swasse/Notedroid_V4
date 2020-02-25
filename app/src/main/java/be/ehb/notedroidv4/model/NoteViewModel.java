package be.ehb.notedroidv4.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Banaan on 20/01/2038. ;)
 */
public class NoteViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Note>> notes;

    public MutableLiveData<ArrayList<Note>> getNotes() {
        if(notes == null){
            notes = new MutableLiveData<>();
            loadNotes();
        }

        return notes;
    }

    public void addNote(Note n){
        notes.getValue().add(n);
    }

    public void deleteNote(Note n) {
        notes.getValue().remove(n);
    }

    private void loadNotes() {
        ArrayList<Note> tempList = new ArrayList<>();

        tempList.add(new Note("Eerste", "Comment"));

        notes.setValue(tempList);
    }
}
