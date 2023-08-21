package com.bridgelabz.bookstore.exception;

public class UserAlreadyPresentException extends RuntimeException {

	public UserAlreadyPresentException() {
		super("email id is already in use");
	}
}
