package com.example.journaling_app.service;

import com.example.journaling_app.entity.Tag;
import com.example.journaling_app.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository;
}
