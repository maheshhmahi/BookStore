package com.bridgelabz.bookstore.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.exception.BookstoreException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book getBookByName(UUID id) {
		return bookRepository.findById(id).get();
	}

	@Override
	public Book createBook(BookDTO bookDTO) {
		List<Book> books=getAllBooks();
		books.forEach(book->{
			if(book.getBookName().equalsIgnoreCase(bookDTO.getBookName()) && book.getAuthorName().equalsIgnoreCase(bookDTO.getAuthorName())) {
				throw new BookstoreException("book already exist");
			}
		});
		Book book = new Book(bookDTO);
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> sortByName() {
		List<Book> contactList=bookRepository.findAll();
		contactList=contactList.stream().sorted(Comparator.comparing(Book::getBookName)).collect(Collectors.toList());
		return contactList;
	}

	@Override
	public List<Book> sortByPrice() {
		List<Book> contactList=bookRepository.findAll();
		contactList=contactList.stream().sorted(Comparator.comparingLong(Book::getActualPrice)).collect(Collectors.toList());
		return contactList;
	}

	@Override
	public List<Book> sortByRatings() {
		List<Book> contactList=bookRepository.findAll();
		contactList=contactList.stream().sorted(Comparator.comparingDouble(Book::getRatings)).collect(Collectors.toList());
		return contactList;
	}

	@Override
	public List<Book> getBooksBySeacrch(String name) {
		return bookRepository.getBooksBySearch(name.toLowerCase());
	}

	@Override
	public List<Book> sortByTime() {
		List<Book> contactList=bookRepository.findAll();
		contactList=contactList.stream().sorted(Comparator.comparing(Book::getCreateTime).reversed()).collect(Collectors.toList());
		return contactList;
	}

}
