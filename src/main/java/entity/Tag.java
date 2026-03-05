package entity;

import java.util.List;

import jakarta.persistence.*;

public class Tag {
	
	@Column(name="tag_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer tagId;
	
	@Column(name="name")
	String name;
	
	@ManyToMany(mappedBy = "tags")
	List<Entry> entries;
}
