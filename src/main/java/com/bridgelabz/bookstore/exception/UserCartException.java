package com.bridgelabz.bookstore.exception;

public class UserCartException extends RuntimeException{
	
	public UserCartException(){
		super("User cart not found!");
	}

}
