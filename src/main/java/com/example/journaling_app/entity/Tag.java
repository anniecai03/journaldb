package com.example.journaling_app.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Tag {
	
	@Column(name="tag_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer tagId;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	private List<Entry> entries;
	
	// Constructors
	public Tag() { 	}
	
	public Tag(Integer id, String name) {
		this.tagId = id;
		this.name = name;
		this.entries = new ArrayList<>();
	}
	
	public Tag(String name) {
		this.name = name;
		this.entries = new ArrayList<>();
	}
	
	public Tag(Integer id, String name, List<Entry> entryList) {
		this.tagId = id;
		this.name = name;
		this.entries = entryList;
	}
	
	// Getters
	public Integer getTagID() {return this.tagId;}
	public String getName() {return this.name;}
	public List<Entry> getEntries() {return this.entries;}
	
	// Setters
	public void setTagID(Integer id) {this.tagId = id;}
	public void setName(String name) {this.name = name;}
	public void setEntries(List<Entry> entryList) {this.entries = entryList;}		
		
}
