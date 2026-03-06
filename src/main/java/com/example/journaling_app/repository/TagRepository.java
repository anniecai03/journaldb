package com.example.journaling_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.journaling_app.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
