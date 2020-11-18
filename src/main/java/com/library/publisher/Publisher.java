package com.library.publisher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long publisherId;
	private String publisherName;
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public void setPublisherDescription(String publisherDescription) {
		this.publisherDescription = publisherDescription;
	}

	private String publisherDescription;

	public Publisher(String name, String description) {
		publisherName = name;
		publisherDescription = description;
	}
	
	public Publisher() {}
	
	public String getPublisherName() {
		return publisherName;
	}
	
	public Long getPublisherId() {
		return publisherId;
	}

	public String getPublisherDescription() {
		return publisherDescription;
	}
	
	public String toString() {
		return publisherName;
	}
}
