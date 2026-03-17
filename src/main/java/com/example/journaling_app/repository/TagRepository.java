package com.example.journaling_app.repository;

import java.time.LocalDateTime;

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
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tag SET name = :nameVar WHERE tag_id = :idVar", nativeQuery = true)
	Integer updateTagByTagId(@Param("nameVar") String name, @Param("idVar") Integer id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE entryTag SET tag_id = :newTag WHERE tag_id = :oldTag", nativeQuery = true)
	Integer combineTags(@Param("newTag") Integer newId, @Param("oldTag") Integer oldId);
}
