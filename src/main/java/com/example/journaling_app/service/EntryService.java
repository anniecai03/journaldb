package com.example.journaling_app.service;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.repository.EntryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
	
	@Autowired
	EntryRepository entryRepository;
	@Autowired
	TagService tagService;
	
	public List<Entry> getAllEntries() {
		return this.entryRepository.findAll();
	}
	
	public List<Entry> getEntriesBySearch(String title, String sort, String direction) {		
		if (title == null) { title = ""; }
		if (sort == null) { sort = "creationDate"; }
		if (direction != null && direction.equals("desc")) { 
			return this.entryRepository.findByTitleContainingIgnoreCase(title, Sort.by(Sort.Direction.DESC, sort));
		}
		
		return this.entryRepository.findByTitleContainingIgnoreCase(title, Sort.by(sort));
	}
	
	public Entry getEntryByID(Integer id) {
		return this.entryRepository.findByEntryId(id);
	}
	
	public Entry createEntry(Entry entry) {
		if (this.isValidEntryTitleAndContent(entry)) {
			
			List<Tag> saveTags = new ArrayList<Tag>();
			
			for(Tag tag : entry.getTags()) {
				Tag dbTag = tagService.getTagByName(tag.getName());
						
				if (dbTag == null) {
					dbTag = tagService.createTag(tag);
				}
				
				saveTags.add(dbTag);
			}
			
			entry.setTags(saveTags);
			return this.entryRepository.save(entry);
		}
		return null;
	}
	
	public Integer updateEntryByID(Entry entry, Integer id) {
		Entry dbEntry = this.getEntryByID(id);
		
		if (dbEntry != null && this.isValidEntryTitleAndContent(entry)) {
			return this.entryRepository.updateEntryByEntryId(entry.getTitle(), 
					entry.getContent(), 
					LocalDateTime.now(), 
					id);
		}
		return 0;
	}
	
	public Integer deleteEntryByID(Integer id) {
		return this.entryRepository.deleteByEntryId(id);
	}
	
	public List<Tag> getTagsByEntryID(Integer id) {
		Entry dbEntry = this.getEntryByID(id);
		
		if (dbEntry != null) {
			return dbEntry.getTags();
		}
		return null;
	}
	
	public List<Entry> getEntriesByTagIDs(List<Integer> ids) {
		return this.entryRepository.findEntriesByTagIds(ids);
	}
	
	public List<Entry> getEntriesBySearchAndTags(String title, String sort, String direction, List<Integer> ids) {		
		List<Entry> searchEntries = this.getEntriesBySearch(title, sort, direction);
		List<Entry> results = new ArrayList<Entry>();
		
		for (Entry entry : searchEntries) {
			for (Tag tag : entry.getTags()) {
				if (ids.contains(tag.getTagId())) {
					results.add(entry);
					break;
				}
			}
		}
		return results;
	}
	
	public List<Entry> filterEntries(String title, String sort, String direction, List<Integer> ids) {
		if (title == null && sort == null && direction == null && ids == null) {
			return this.entryRepository.findAll();
		} else if (ids == null) {
			return this.getEntriesBySearch(title, sort, direction);
		} else if (title == null && sort == null && direction == null) {
			return this.getEntriesByTagIDs(ids);
		}
		return this.getEntriesBySearchAndTags(title, sort, direction, ids);
	}
	
	public Boolean isValidEntryTitleAndContent(Entry entry) {
		return entry.getTitle().trim().length() > 0 && entry.getContent().trim().length() > 0;
	}

}
