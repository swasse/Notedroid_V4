package be.ehb.notedroidv4.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Banaan on 20/01/2038. ;)
 */


public class NoteViewModel extends AndroidViewModel {

    private LiveData<List<Note>> notes;
    private NotesDatabase database;
    private final Application mApplication;

    public NoteViewModel(Application application) {
        super(application);
        mApplication = application;
        database = NotesDatabase.getInstance(application);

        notes = database.getRepoDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mApplication);
        String chosen = prefs.getString("pref_order", "Alfabetical");
        switch (chosen){
            case "Alfabetical": notes = database.getRepoDao().getAllNotes();
            break;
            case "Chronological": notes = database.getRepoDao().getAllNotesChronological();
            break;
        }

        return notes;
    }

    //tricky, not allowed to access on main thread
    public void insertNote(Note n){
        NotesDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getRepoDao().insertNote(n);
            }
        });
    }

    public void updateNote(Note n){
        NotesDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getRepoDao().updateNote(n);
            }
        });
    }

    public void deleteNote(Note n){
        NotesDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                database.getRepoDao().deleteNote(n);
            }
        });
    }

}
