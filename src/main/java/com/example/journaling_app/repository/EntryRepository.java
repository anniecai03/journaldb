package com.example.journaling_app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.example.journaling_app.entity.Entry;

import jakarta.transaction.Transactional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
	
	@Transactional
	@Modifying
	Integer deleteByEntryId(Integer id);
	
	Entry findByEntryId(Integer id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE entry SET title = :titleVar AND content = :contentVar AND edit_date = :dateVar WHERE entry_id = :idVar", nativeQuery = true)
	Integer updateEntryByEntryId(@Param("titleVar") String title, @Param("contentVar") String content, @Param("dateVar") LocalDateTime editDate, @Param("idVar") Integer id);
	
	@Query(value = "SELECT DISTINCT entry.* FROM entry JOIN entrytag ON entry.entry_id = entrytag.entry_id WHERE entrytag.tag_id IN (:tagIdVars)", nativeQuery = true)
	List<Entry> findEntriesByTagIds(@Param("tagIdVars") List<Integer> idVars);
	
}
