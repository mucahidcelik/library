package com.library.book;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.library.author.Author;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findByBookTitle(String bookTitle);

	List<Book> findByBookSubTitle(String bookSubTitle);

	List<Book> findByAuthor(Author author);

	List<Book> findByISBN(String ISBN);

	List<Book> findByBookSerialName(String ISBN);
}
