package com.wayne.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wayne.domain.Book;
import com.wayne.service.BookService;
import com.wayne.service.BookServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class BookApi {

//	@Autowired
//	private BookService bookService;

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@GetMapping("/books")
	public ResponseEntity<?> listAllBooks() {
		List<Book> books = bookServiceImpl.findAllBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBook(@PathVariable Long id) {
		Book book = bookServiceImpl.getBookById(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);

	}

	@PostMapping("/books")
	public ResponseEntity<?> saveBook(Book book) {
		Book book1 = bookServiceImpl.saveBook(book);
		return new ResponseEntity<Book>(book1, HttpStatus.CREATED);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, Book book) {
		Book currentBook = bookServiceImpl.getBookById(id);
		BeanUtils.copyProperties(book, currentBook);
		Book book2 = bookServiceImpl.updateBook(currentBook);
		return new ResponseEntity<Book>(book2, HttpStatus.OK);
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookServiceImpl.deleteBookById(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/books/")
	public ResponseEntity<?> deleteAllBooks() {
		bookServiceImpl.deleteAllBook();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
