package be.ehb.notedroidv4.model;


import org.threeten.bp.LocalDate;

import java.io.Serializable;

public class Note implements Serializable {

	private String title;
	private String content;
	private LocalDate publishDate;
    private LocalDate lastModifiedDate;

	//constructors
    public Note() {
    }

    public Note(String title, String content) {
		this.title = title;
		this.content = content;
		this.publishDate = LocalDate.now();
        this.lastModifiedDate = LocalDate.now();
	}

    //getters en setters
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
