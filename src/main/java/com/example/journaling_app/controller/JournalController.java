package com.example.journaling_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.service.EntryService;
import com.example.journaling_app.service.TagService;

import java.util.List;

@RestController
@RequestMapping
public class JournalController {
	
	@Autowired
	EntryService entryService;
	@Autowired
	TagService tagService;
	
	@GetMapping("/entries")
	public ResponseEntity<List<Entry>> getAllEntries(@RequestParam(required = false) String search, 
													 @RequestParam(required = false) String sort, 
													 @RequestParam(required = false) String direction) {
		List<Entry> results = this.entryService.getEntriesBySearch(search, sort, direction);
		return ResponseEntity.status(200).body(results);		
	}
	
	@GetMapping("/entries/{entryId}")
	public ResponseEntity<Entry> getEntryByID(@PathVariable Integer entryId) {
		Entry dbEntry = this.entryService.getEntryByID(entryId);
		
		if (dbEntry == null) {
			return ResponseEntity.status(404).body(dbEntry);
		}
		return ResponseEntity.status(200).body(dbEntry);
	}
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> createEntry(@RequestBody Entry entry) {
		Entry newEntry = this.entryService.createEntry(entry);
				
		if (newEntry == null) {
			return ResponseEntity.status(400).body(newEntry);
		}
		return ResponseEntity.status(200).body(newEntry);
	}
	
	@PutMapping("/entries/{entryId}")
	public ResponseEntity<Integer> updateEntryByID(@RequestBody Entry entry, @PathVariable Integer entryId) {
		Integer updateCount = this.entryService.updateEntryByID(entry, entryId);
		
		if (updateCount == 0) {
			return ResponseEntity.status(404).body(updateCount);
		}
		return ResponseEntity.status(200).body(updateCount);	
	}
	
	@DeleteMapping("/entries/{entryId}")
	public ResponseEntity<Integer> deleteEntryByID(@PathVariable Integer entryId) {
		Integer deleteCount = this.entryService.deleteEntryByID(entryId);
		
		if (deleteCount == 0) {
			return ResponseEntity.status(404).body(deleteCount);
		}
		return ResponseEntity.status(200).body(deleteCount);
	}
	
	@GetMapping("/entries/{entryId}/tags")
	public ResponseEntity<List<Tag>> getTagsByEntryID(@PathVariable Integer entryId) {
		List<Tag> tags = this.entryService.getTagsByEntryID(entryId);
		
		if (tags == null) {
			return ResponseEntity.status(404).body(tags);
		}
		
		return ResponseEntity.status(200).body(tags);
	}
	
	@GetMapping("/tags")
	public ResponseEntity<List<Tag>> getAllTags() {
		return ResponseEntity.status(200).body(this.tagService.getAllTags());
	}
	
	@PostMapping("/tags")
	public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
		Tag newTag = this.tagService.createTag(tag);
		return ResponseEntity.status(200).body(newTag);
	}
	
	@DeleteMapping("/tag/{tagId}")
	public ResponseEntity<Integer> deleteTag(@PathVariable Integer tagId) {
		Integer deleteCount = this.tagService.deleteTagByID(tagId);
		
		if (deleteCount == 0) {
			return ResponseEntity.status(404).body(deleteCount);
		}
		return ResponseEntity.status(200).body(deleteCount);
	}
	
	@GetMapping("/tags/{tagId}/entries")
	public ResponseEntity<List<Entry>> getEntriesByTagID(@PathVariable Integer tagId) {
		List<Entry> entries = this.tagService.getEntriesByTagID(tagId);
		
		if (entries == null) {
			return ResponseEntity.status(404).body(entries);
		}
		
		return ResponseEntity.status(200).body(entries);	
	}
	
	@GetMapping("/tags/entries")
	public ResponseEntity<List<Entry>> getEntriesByTags(@RequestParam List<Integer> tagIds) {
		List<Entry> entries = this.entryService.getEntriesByTagIDs(tagIds);
		
		if (entries == null) {
			return ResponseEntity.status(404).body(entries);
		}
		
		return ResponseEntity.status(200).body(entries);	
	}	

}
