package com.library;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.library.author.Author;
import com.library.author.AuthorRepository;
import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.publisher.Publisher;
import com.library.publisher.PublisherRepository;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	@Autowired
	private BookRepository books;
	@Autowired
	private AuthorRepository authors;
	@Autowired
	private PublisherRepository publishers;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		authors.save(new Author("Yazar Adı 1", "Yazar Açıklaması 1"));
		authors.save(new Author("Yazar Adı 2", "Yazar Açıklaması 2"));
		authors.save(new Author("Yazar Adı 3", "Yazar Açıklaması 3"));

		publishers.save(new Publisher("Yayınevi Adı 1", "Yayınevi Açıklaması 1"));
		publishers.save(new Publisher("Yayınevi Adı 2", "Yayınevi Açıklaması 2"));
		publishers.save(new Publisher("Yayınevi Adı 3", "Yayınevi Açıklaması 3"));
		
		Iterator<Author> aIterator = authors.findAll().iterator();
		Iterator<Publisher> pIterator = publishers.findAll().iterator();
		
		books.save(new Book("Kitap Adı 1", "Kitap Alt Adı 1", "Kitap Seri Adı 1", "Kitap Açıklaması 1",
				aIterator.next(), pIterator.next(), "123456789"));
		books.save(new Book("Kitap Adı 2", "Kitap Alt Adı 2", "Kitap Seri Adı 2", "Kitap Açıklaması 2",
				aIterator.next(), pIterator.next(), "123456790"));
		books.save(new Book("Kitap Adı 3", "Kitap Alt Adı 3", "Kitap Seri Adı 3", "Kitap Açıklaması 3",
				aIterator.next(), pIterator.next(), "123456791"));

	}
}
