package com.library.author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}

	public Author() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long authorId;
	private String authorName;
	private String authorDescription;

	public Long getAuthorId() {
		return authorId;
	}

	public Author(String name, String description) {
		authorName = name;
		authorDescription = description;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getAuthorDescription() {
		return authorDescription;
	}

	public String toString() {
		return "Author: " + authorName;
	}
}
