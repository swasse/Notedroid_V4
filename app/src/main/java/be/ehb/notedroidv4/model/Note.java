package be.ehb.notedroidv4.model;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;

import java.io.Serializable;

@Entity
public class Note implements Serializable {

	//attributes in tabel = variabelen in klasse
	@PrimaryKey(autoGenerate = true)
	private long id;
	private String title;
	private String content;
	private LocalDate publishDate;
    private LocalDate lastModifiedDate;

	//constructors
    public Note() {
    }

    @Ignore
    public Note(String title, String content) {
		this.title = title;
		this.content = content;
		this.publishDate = LocalDate.now();
        this.lastModifiedDate = LocalDate.now();
	}

    //getters en setters

	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
    public LocalDate getLastModifiedDate() {
        return lastModifiedDate;
    }
	public void setId(long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
    public void setLastModifiedDate(LocalDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
