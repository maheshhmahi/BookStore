package com.bridgelabz.bookstore.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String email){
		super("User not found with email id : "+email);
	}

	public UserNotFoundException(UUID id) {
		super("User not found with user id : "+id);
		}
}
