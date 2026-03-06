package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.journaling_app.repository.EntryRepository;
import com.example.journaling_app.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping
public class JournalController {
	
	@Autowired
	EntryRepository entryRepository;
	TagRepository tagRepository;
	
	@GetMapping("/entries")
	public void getAllEntries() {
		
	}
	
	@GetMapping("/entries/{id}")
	public void getEntryByID() {
		
	}
	
	@PostMapping("/entries")
	public void createEntry() {
		
	}
	
	@PutMapping("/entries/{id}")
	public void updateEntryByID() {
		
	}
	
	@DeleteMapping("/entries/{id}")
	public void deleteEntryByID() {
		
	}
	
	@GetMapping("/tags")
	public void getAllTags() {
		
	}
	
	@GetMapping("/entries/{id}/tags")
	public void getTagsByEntryID() {
		
	}
	
	@PostMapping("/tags")
	public void createTag() {
		
	}
	
	@DeleteMapping("/tag/{id}")
	public void deleteTag() {
		
	}
	
	@GetMapping("/tags/{id}/entries")
	public void getEntriesByTags() {
		
	}
	

}
