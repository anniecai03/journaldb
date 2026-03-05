package entity;

import java.time.*;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "entry")
public class Entry {
	
	@Column(name="entry_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	Integer entryId;
	
	@Column(name="title")
	String title;
	
	@Column(name="content")
	String content;
	
	@Column(name="creation_date")
	LocalDate creationDate;
	
	@Column(name="edit_date")
	LocalDate editDate;
	
	@ManyToMany
	@JoinTable(
		name = "entrytag",
		joinColumns = @JoinColumn(name = "entry_id"),
		inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	List<Tag> tags;
	
}
