package com.example.journaling_app;

import com.example.journaling_app.entity.Entry;
import com.example.journaling_app.repository.EntryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EntryRepositoryTests {

    @Autowired
    private EntryRepository entryRepository;

    @Test
    void findAllTest() {

        Entry entry1 = new Entry("test 1", "test 1");
        Entry entry2 = new Entry("test 2", "test 2");

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        List<Entry> entries = entryRepository.findAll();

        assertEquals(2, entries.size());
        assertTrue(entries.stream().anyMatch(e -> e.getTitle().equals("test 1")));
        assertTrue(entries.stream().anyMatch(e -> e.getTitle().equals("test 2")));
    }
    
    @Test
    void findByEntryIdTest() {

        Entry entry1 = new Entry("test 1", "test 1");
        Entry entry2 = new Entry("test 2", "test 2");

        entryRepository.save(entry1);
        entryRepository.save(entry2);

        List<Entry> entries = entryRepository.findAll();

        assertEquals(2, entries.size());
        assertTrue(entries.stream().anyMatch(e -> e.getTitle().equals("test 1")));
        assertTrue(entries.stream().anyMatch(e -> e.getTitle().equals("test 2")));
    }
}
