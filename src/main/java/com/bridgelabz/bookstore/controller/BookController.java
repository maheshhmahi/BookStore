package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.BookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/")
	public ResponseEntity<ResponseDTO> getAllBooks() {
		List<Book> bookList = bookService.getAllBooks();
		ResponseDTO respDTO = new ResponseDTO("Get call for Book success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	@GetMapping("/name")
	public ResponseEntity<ResponseDTO> getAllBooksSortByName() {
		List<Book> bookList = bookService.sortByName();
		ResponseDTO respDTO = new ResponseDTO("sort by name Book success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	@GetMapping("/price")
	public ResponseEntity<ResponseDTO> getAllBooksSortByPrice() {
		List<Book> bookList = bookService.sortByPrice();
		ResponseDTO respDTO = new ResponseDTO("sortby price for Book success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	@GetMapping("/ratings")
	public ResponseEntity<ResponseDTO> getAllBooksSortByRatings() {
		List<Book> bookList = bookService.sortByRatings();
		ResponseDTO respDTO = new ResponseDTO("sort by ratings for Book success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	@GetMapping("/time")
	public ResponseEntity<ResponseDTO> getAllBooksSortByTime() {
		List<Book> bookList = bookService.sortByTime();
		ResponseDTO respDTO = new ResponseDTO("sort by ratings for Book success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("id") UUID id) {
		Book book = bookService.getBookByName(id);
		ResponseDTO respDTO = new ResponseDTO("Get call for Book success", book);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<ResponseDTO> getBooksByName(@PathVariable("name") String name){
		List<Book> bookList=bookService.getBooksBySeacrch(name);
		ResponseDTO respDTO = new ResponseDTO(" Book Search success", bookList);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> postBook(@RequestBody BookDTO bookDTO) {
		Book book = bookService.createBook(bookDTO);
		ResponseDTO respDTO = new ResponseDTO(" Book created success", book);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
