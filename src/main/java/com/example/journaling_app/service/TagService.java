package com.example.journaling_app.service;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.repository.TagRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository;
	
	public List<Tag> getAllTags() {
		return this.tagRepository.findAll();
	}
	
	public Tag getTagByID(Integer id) {
		return this.tagRepository.findByTagId(id);
	}
	
	public Tag getTagByName(String name) {
		return this.tagRepository.findByName(name);
	}
	
	public Tag createTag(Tag tag) {
		return this.tagRepository.save(tag);
	}
	
	public Integer deleteTagByID(Integer id) {
		return this.tagRepository.deleteByTagId(id);
	}
	
	public List<Entry> getEntriesByTagID(Integer id) {
		Tag dbTag = this.tagRepository.findByTagId(id);
		
		if (dbTag != null) {
			return dbTag.getEntries();
		}
		return null;
	}
}
