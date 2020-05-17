package com.wayne.dto;

import com.wayne.domain.Book;
import com.wayne.util.CustomBeanUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

public class BookDTO {

	@NotBlank
	private String author;
	@Length(max = 20)
	private String description;
	@NotBlank
	private String name;
	@NotNull
	private Integer status;

	public BookDTO() {
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void convertToBook(Book book) {
		new BookConvert().convert(this, book);
	}

	public Book convertToBook() {
		return new BookConvert().convert(this);
	}

	private class BookConvert implements Convert<BookDTO, Book> {

		@Override
		public Book convert(BookDTO bookDTO, Book book) {
			String[] nullPropertyNames = CustomBeanUtils.getNullPropertyNames(bookDTO);
			BeanUtils.copyProperties(bookDTO, book, nullPropertyNames);
			return book;
		}

		@Override
		public Book convert(BookDTO bookDTO) {
			Book book = new Book();
			BeanUtils.copyProperties(bookDTO, book);
			return book;
		}
	}
}
