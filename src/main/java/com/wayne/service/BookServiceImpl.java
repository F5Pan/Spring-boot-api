package com.wayne.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wayne.domain.Book;
import com.wayne.domain.BookRespository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRespository respository;

	@Override
	public List<Book> findAllBooks() {
		return respository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return respository.findOne(id);
	}

	@Override
	public Book saveBook(Book book) {
		return respository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return respository.save(book);
	}

	@Override
	public void deleteBookById(Long id) {
		respository.delete(id);

	}

	@Override
	public void deleteAllBook() {
		respository.deleteAll();

	}

}
