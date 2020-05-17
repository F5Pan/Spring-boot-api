package com.wayne.api;

import java.beans.PropertyDescriptor;
import java.util.List;

import javax.persistence.Convert;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wayne.domain.Book;
import com.wayne.dto.BookDTO;
import com.wayne.exception.InvalidRequestException;
import com.wayne.exception.NotFoundException;
import com.wayne.service.BookService;

@RestController
@RequestMapping("/api/v1")
public class BookApi {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<?> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			throw new NotFoundException("Books not found");
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@GetMapping("/books/{id}")
	public ResponseEntity<?> getBook(@PathVariable Long id) {
		Book book = bookService.getBookById(id);
		if (book == null) {
			throw new NotFoundException(String.format("Book by id %s not found", id));

		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);

	}

	@PostMapping("/books")
	public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid parameter", bindingResult);
		}

		Book book1 = bookService.saveBook(bookDTO.convertToBook());

		return new ResponseEntity<Book>(book1, HttpStatus.CREATED);
	}

	@PutMapping("/books/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO,
			BindingResult bindingResult) {
		Book currentBook = bookService.getBookById(id);
		if (currentBook == null) {
			throw new NotFoundException(String.format("Book by id %s not found", id));
		}
		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid parameter", bindingResult);
		}
		bookDTO.convertToBook(currentBook);
		Book book1 = bookService.updateBook(currentBook);
		return new ResponseEntity<Object>(book1, HttpStatus.OK);
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Long id) {
		bookService.deleteBookById(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/books/")
	public ResponseEntity<?> deleteAllBooks() {
		bookService.deleteAllBook();
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
