package com.example.journaling_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.entity.Tag;

import jakarta.transaction.Transactional;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	Tag findByTagId(Integer id);
	Tag findByName(String name);
	
	@Transactional
	@Modifying
	Integer deleteByTagId(Integer id);

}
