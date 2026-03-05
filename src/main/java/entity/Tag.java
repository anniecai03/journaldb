package entity;

import jakarta.persistence.*;

public class Tag {
	
	@Column(name="tag_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer entryId;
	
	@Column(name="name")
	String name;
}
