package com.bridgelabz.bookstore.exception;

public class BookNotFoundException extends RuntimeException{
	
	public BookNotFoundException(){
		super("Book not found!");
	}

}
