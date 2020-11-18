package com.library.publisher;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
	List<Publisher> findByPublisherName(String publisherName);
}
