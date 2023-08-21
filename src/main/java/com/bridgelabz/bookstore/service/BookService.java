package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.UUID;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;

public interface BookService {

	Book getBookByName(UUID id);

	Book createBook(BookDTO bookDTO);

	List<Book> getAllBooks();

	List<Book> sortByName();

	List<Book> sortByPrice();

	List<Book> sortByRatings();

	List<Book> getBooksBySeacrch(String name);

	List<Book> sortByTime();

}
