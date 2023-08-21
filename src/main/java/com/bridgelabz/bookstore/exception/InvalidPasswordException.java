package com.bridgelabz.bookstore.exception;

public class InvalidPasswordException extends RuntimeException {
public InvalidPasswordException() {
	super("invalid password");
}
}
