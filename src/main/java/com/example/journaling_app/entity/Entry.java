package com.example.journaling_app.entity;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "entry")
public class Entry {
	
	@Column(name="entry_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer entryId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="content")
	private String content;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	
	@Column(name="edit_date")
	private LocalDateTime editDate;
	
	@ManyToMany
	@JoinTable(
		name = "entrytag",
		joinColumns = @JoinColumn(name = "entry_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;
	
	// Constructors
	public Entry() { 	}
	
	public Entry(String title, String content, LocalDateTime creationDate, LocalDateTime editDate) {
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.editDate = editDate;
		this.tags = new ArrayList<>();
	}
		
	public Entry(Integer id, String title, String content, LocalDateTime creationDate, LocalDateTime editDate) {
		this.entryId = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.editDate = editDate;
		this.tags = new ArrayList<>();
	}
		
	public Entry(Integer id, String title, String content, LocalDateTime creationDate, LocalDateTime editDate, List<Tag> tagList) {
		this.entryId = id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.editDate = editDate;
		this.tags = tagList;
	}
		
	// Getters
	public Integer getEntryID() {return this.entryId;}
	public String getTitle() {return this.title;}
	public String getContent() {return this.content;}
	public LocalDateTime getCreationDate() {return this.creationDate;}
	public LocalDateTime getEditDate() {return this.editDate;}
	public List<Tag> getEntries() {return this.tags;}
		
	// Setters
	public void setEntryID(Integer id) {this.entryId = id;}
	public void setTitle(String title) {this.title = title;}
	public void setContent(String content) {this.content = content;}
	public void setCreationDate(LocalDateTime date) {this.creationDate = date;}
	public void setEditDate(LocalDateTime date) {this.editDate = date;}
	public void setTags(List<Tag> tagList) {this.tags = tagList;}
	
	public void addTag(Tag tag) {
		this.tags.add(tag);
		tag.getEntries().add(this);
	}
	
	public void removeTag(Tag tag) {
		this.tags.remove(tag);
		tag.getEntries().remove(this);
	}
	
}
