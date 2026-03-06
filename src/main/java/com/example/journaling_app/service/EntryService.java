package com.example.journaling_app.service;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.repository.EntryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {
	
	@Autowired
	EntryRepository entryRepository;
	

}
