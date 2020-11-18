package com.library.author;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	List<Author> findByAuthorName(String authorName);
}
