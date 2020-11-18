package com.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.author.Author;
import com.library.author.AuthorRepository;
import com.library.book.Book;
import com.library.book.BookRepository;
import com.library.publisher.Publisher;
import com.library.publisher.PublisherRepository;

@Controller
public class LibraryController {
	@Autowired
	private AuthorRepository authors;
	@Autowired
	private PublisherRepository publishers;
	@Autowired
	private BookRepository books;

	@GetMapping("/book/{param}")
	String bookDetailPage(ModelMap map, @PathVariable("param") String param) {
		map.put("authors", authors.findAll());
		map.put("publishers", publishers.findAll());
		if (books.findByBookTitle(param).size() > 0) {
			map.put("book", books.findByBookTitle(param).get(0));
			return "book-detail";
		} else {
			return "not-found";
		}
	}

	@GetMapping("/author/{param}")
	String authorDetailPage(ModelMap map, @PathVariable("param") String param) {
		if (authors.findByAuthorName(param).size() > 0) {
			map.put("author", authors.findByAuthorName(param).get(0));
			return "author-detail";
		} else {
			return "not-found";
		}
	}

	@GetMapping("/publisher/{param}")
	String publisherDetailPage(ModelMap map, @PathVariable("param") String param) {
		if (publishers.findByPublisherName(param).size() > 0) {
			map.put("publisher", publishers.findByPublisherName(param).get(0));
			return "publisher-detail";
		} else {
			return "not-found";
		}

	}

	@GetMapping("/")
	String detailPage(Model model) {
		model.addAttribute("authors", authors.findAll());
		model.addAttribute("publishers", publishers.findAll());
		return "index";
	}

	@PostMapping(value = "/search")
	String searchPage(String q, int type, ModelMap map) {
		List<Book> bookResults = new ArrayList<Book>();
		List<Author> authorResults = new ArrayList<Author>();
		List<Publisher> publisherResults = new ArrayList<Publisher>();

		if (type == 1) { // book
			if (q.isEmpty()) {
				books.findAll().forEach(bookResults::add);
			} else {
				bookResults.addAll(books.findByBookTitle(q));
				bookResults.addAll(books.findByISBN(q));
				bookResults.addAll(books.findByBookSerialName(q));
				for (Author a : authors.findByAuthorName(q)) {
					bookResults.addAll(books.findByAuthor(a));
				}
			}
		} else if (type == 2) { // author
			if (q.isEmpty()) {
				authors.findAll().forEach(authorResults::add);
			} else {
				authorResults.addAll(authors.findByAuthorName(q));
			}
		} else if (type == 3) { // publisher
			if (q.isEmpty()) {
				publishers.findAll().forEach(publisherResults::add);
			} else {
				publisherResults.addAll(publishers.findByPublisherName(q));
			}
		}

		map.put("bookResults", bookResults);
		map.put("authorResults", authorResults);
		map.put("publisherResults", publisherResults);
		map.put("books", books.findAll());
		map.put("authors", authors.findAll());
		map.put("publishers", publishers.findAll());
		map.put("type", type);
		map.put("q", q);
		return "result";
	}

	@PostMapping(value = "/saveAuthor")
	String saveAuthor(String id, String authorName, String authorDescription) {
		if(!id.isEmpty()) {
			Author a = authors.findById(Long.parseLong(id)).isPresent() ? authors.findById(Long.parseLong(id)).get() : null;
			if(a!=null) {
				a.setAuthorDescription(authorDescription);
				a.setAuthorName(authorName);
				authors.save(a);
				return "redirect:/author/" + authorName;
			}
		}
		authors.save(new Author(authorName, authorDescription));
		return "redirect:/author/" + authorName;
	}

	@PostMapping(value = "/savePublisher")
	String savePublisher(String id, String publisherName, String publisherDescription) {
		if(!id.isEmpty()) {
			Publisher p = publishers.findById(Long.parseLong(id)).isPresent() ? publishers.findById(Long.parseLong(id)).get() : null;
			if(p!=null) {
				p.setPublisherDescription(publisherDescription);
				p.setPublisherName(publisherName);
				publishers.save(p);
				return "redirect:/publisher/" + publisherName;
			}
		}
		publishers.save(new Publisher(publisherName, publisherDescription));
		return "redirect:/publisher/" + publisherName;
	}

	@PostMapping(value = "/saveBook")
	String saveBook(String id, String bookTitle, String bookSubTitle, String bookSerialName, String bookDescription,
			long authorId, long publisherId, String ISBN) {
		Author a = authors.findById(authorId).isPresent() ? authors.findById(authorId).get() : null;
		Publisher p = publishers.findById(publisherId).isPresent() ? publishers.findById(publisherId).get() : null;
		if (!id.isEmpty()) {
			Book b = books.findById(Long.parseLong(id)).isPresent() ? books.findById(Long.parseLong(id)).get() : null;
			if (b != null) {
				b.setBookTitle(bookTitle);
				b.setBookSubTitle(bookSubTitle);
				b.setBookSerialName(bookSerialName);
				b.setBookDescription(bookDescription);
				b.setAuthor(a);
				b.setPublisher(p);
				b.setISBN(ISBN);
				books.save(b);
				return "redirect:/book/" + bookTitle;
			}
		}
		books.save(new Book(bookTitle, bookSubTitle, bookSerialName, bookDescription, a, p, ISBN));
		return "redirect:/book/" + bookTitle;

	}

	@PostMapping("/deleteBook")
	String deleteBook(String id) {
		books.deleteById(Long.parseLong(id));
		return "redirect:/";
	}
	
	@PostMapping("/deleteAuthor")
	String deleteAuthor(String id) {
		authors.deleteById(Long.parseLong(id));
		return "redirect:/";
	}
	
	@PostMapping("/deletePublisher")
	String deletePublisher(String id) {
		publishers.deleteById(Long.parseLong(id));
		return "redirect:/";
	}

	@GetMapping("/redirectToHome")
	String redirectToHome() {
		return "redirect:/";
	}

	@GetMapping("/findOneBook")
	@ResponseBody
	public Book findOneBook(Long id) {
		return books.findById(id).isPresent() ? books.findById(id).get() : null;
	}
	
	@GetMapping("/findOneAuthor")
	@ResponseBody
	public Author findOneAuthor(Long id) {
		return authors.findById(id).isPresent() ? authors.findById(id).get() : null;
	}
	
	@GetMapping("/findOnePublisher")
	@ResponseBody
	public Publisher findOnePublisher(Long id) {
		return publishers.findById(id).isPresent() ? publishers.findById(id).get() : null;
	}

}
