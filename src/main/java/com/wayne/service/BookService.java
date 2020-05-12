package com.wayne.service;

import java.util.List;

import com.wayne.domain.Book;

public interface BookService {

	List<Book> findAllBooks();

	Book getBookById(Long id);

	Book saveBook(Book book);

	Book updateBook(Book book);

	void deleteBookById(Long id);

	void deleteAllBook();
}
