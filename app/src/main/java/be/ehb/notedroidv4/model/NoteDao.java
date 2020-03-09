package be.ehb.notedroidv4.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Note ORDER BY title")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note ORDER BY lastModifiedDate ASC")
    LiveData<List<Note>> getAllNotesChronological();

    //niet gebruikt, voorbeeld om parameter in query mee te geven
    @Query("SELECT * FROM Note WHERE id = :id ")
    Note findByID(long id);

    @Delete
    void deleteNote(Note n);

    @Insert
    void insertNote(Note n);

    @Update
    void updateNote(Note n);


}
