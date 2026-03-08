package com.example.journaling_app.service;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Entry getEntryByID(Integer id) {
		return this.entryRepository.findByEntryId(id);
	}
	
	public Entry createEntry(Entry entry) {
		if (entry.getTitle().length() > 0 && entry.getContent().length() > 0) {
			
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
		Boolean validEntryContents = entry.getTitle().length() > 0 && entry.getContent().length() > 0;
		
		if (dbEntry != null && validEntryContents) {
			return this.entryRepository.updateEntryByEntryId(entry.getTitle(), 
					entry.getContent(), 
					entry.getEditDate(), 
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

}
