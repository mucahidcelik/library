package com.library.book;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.author.Author;
import com.library.publisher.Publisher;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Book() {
	}

	private String bookTitle;
	private String bookSubTitle;
	private String bookSerialName;
	private String bookDescription;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "authorId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Author author;

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookSubTitle(String bookSubTitle) {
		this.bookSubTitle = bookSubTitle;
	}

	public void setBookSerialName(String bookSerialName) {
		this.bookSerialName = bookSerialName;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "publisherId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore 
	private Publisher publisher;

	private String ISBN;

	public Book(String title, String subTitle, String serialName, String description, Author author,
			Publisher publisher, String ISBN) {
		bookTitle = title;
		bookSubTitle = subTitle;
		bookSerialName = serialName;
		bookDescription = description;
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
	}

	public Long getId() {
		return id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookSubTitle() {
		return bookSubTitle;
	}

	public String getBookSerialName() {
		return bookSerialName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public Author getAuthor() {
		return author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public String getISBN() {
		return ISBN;
	}

	public String toString() {
		return bookTitle;
	}

}
