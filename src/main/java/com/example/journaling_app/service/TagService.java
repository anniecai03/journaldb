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
	
	public Tag getTagByName(Tag tag) {
		return this.tagRepository.findByName(tag.getName());
	}
	
	public Tag createTag(Tag tag) {
		if (this.isAvailableTag(tag.getName())) {
			return this.tagRepository.save(tag);
		}
		return null;
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
	
	public Integer updateTagByID(Tag tag, Integer id) {
		Tag dbTag = this.tagRepository.findByTagId(id);
		
		if (dbTag != null && this.isAvailableTag(tag.getName())) {
			return this.tagRepository.updateTagByTagId(tag.getName(), id);
		} else if (dbTag != null) {
			Integer newId = this.getTagByName(tag).getTagId();
			return this.tagRepository.combineTags(newId, id);
		}
		
		return 0;		
	};
	
	public Boolean isAvailableTag(String name) {
		return this.tagRepository.findByName(name) == null;
	}
}
