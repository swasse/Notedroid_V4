package be.ehb.notedroidv4.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import be.ehb.notedroidv4.model.util.Converters;

@Database(version = 1, entities = {Note.class}, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NotesDatabase extends RoomDatabase {

	private static final String DB_NAME = "noteDatabase.db";
	private static NotesDatabase instance;

	//can be used for threading on the background for i.e. inserts
	static final ExecutorService databaseWriteExecutor =
			Executors.newFixedThreadPool(4);

	public static NotesDatabase getInstance(Context context) {
		if (instance == null) {
			instance = Room.databaseBuilder(
					context,
					NotesDatabase.class,
					DB_NAME).build();
		}
		return instance;
	}

	public abstract NoteDao getRepoDao();
}
