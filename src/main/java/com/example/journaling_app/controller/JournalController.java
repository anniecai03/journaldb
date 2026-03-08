package com.example.journaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.repository.EntryRepository;
import com.example.journaling_app.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping
public class JournalController {
	
	@Autowired
	EntryRepository entryRepository;
	@Autowired
	TagRepository tagRepository;
	
	@GetMapping("/entries")
	public ResponseEntity<List<Entry>> getAllEntries() {
		return ResponseEntity.status(200).body(this.entryRepository.findAll());		
	}
	
	@GetMapping("/entries/{entryId}")
	public ResponseEntity<Entry> getEntryByID(@PathVariable Integer entryId) {
		Entry dbEntry = this.entryRepository.findByEntryId(entryId);
		
		if (dbEntry == null) {
			return ResponseEntity.status(400).body(dbEntry);
		}
		return ResponseEntity.status(200).body(dbEntry);
		
	}
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
		Entry newEntry = this.entryRepository.save(entry);
				
		if (newEntry == null) {
			return ResponseEntity.status(400).body(newEntry);
		}
		return ResponseEntity.status(200).body(newEntry);
	}
	
	@PutMapping("/entries/{entryId}")
	public ResponseEntity<Integer> updateEntryByID(@RequestBody Entry entry, @PathVariable Integer entryId) {
		Entry dbEntry = this.entryRepository.findByEntryId(entryId);
		
		if (dbEntry == null) {
			return ResponseEntity.status(400).body(null);
		}
		
		Integer updateCount = this.entryRepository.updateEntryByEntryId(entry.getTitle(), 
				entry.getContent(), 
				entry.getEditDate(), 
				entryId);
		
		if (updateCount == 0) {
			return ResponseEntity.status(400).body(updateCount);
		}
		return ResponseEntity.status(200).body(updateCount);
		
	}
	
	@DeleteMapping("/entries/{entryId}")
	public ResponseEntity<Integer> deleteEntryByID(@PathVariable Integer entryId) {
		Integer deleteCount = this.entryRepository.deleteByEntryId(entryId);
		
		if (deleteCount == 0) {
			return ResponseEntity.status(400).body(deleteCount);
		}
		return ResponseEntity.status(200).body(deleteCount);
	}
	
	@GetMapping("/tags")
	public ResponseEntity<List<Tag>> getAllTags() {
		return ResponseEntity.status(200).body(this.tagRepository.findAll());
		
	}
	
	@GetMapping("/entries/{entryId}/tags")
	public ResponseEntity<List<Tag>> getTagsByEntryID(@PathVariable Integer entryId) {
		Entry dbEntry = this.entryRepository.findByEntryId(entryId);
		
		if (dbEntry == null) {
			return ResponseEntity.status(402).body(null);
		}
		
		return ResponseEntity.status(200).body(dbEntry.getTags());
	}
	
	@PostMapping("/tags")
	public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
		Tag newTag = this.tagRepository.save(tag);
		return ResponseEntity.status(200).body(newTag);
	}
	
	@DeleteMapping("/tag/{tagId}")
	public ResponseEntity<Integer> deleteTag(@PathVariable Integer tagId) {
		Integer deleteCount = this.tagRepository.deleteByTagId(tagId);
		
		if (deleteCount == 0) {
			return ResponseEntity.status(400).body(deleteCount);
		}
		return ResponseEntity.status(200).body(deleteCount);
	}
	
	@GetMapping("/tags/{tagId}/entries")
	public ResponseEntity<List<Entry>> getEntriesByTags(@PathVariable Integer tagId) {
		Tag dbTag = this.tagRepository.findByTagId(tagId);
		
		if (dbTag == null) {
			return ResponseEntity.status(400).body(null);
		}
		
		return ResponseEntity.status(200).body(dbTag.getEntries());	
	}
	

}
