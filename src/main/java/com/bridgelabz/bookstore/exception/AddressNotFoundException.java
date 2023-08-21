package com.bridgelabz.bookstore.exception;

public class AddressNotFoundException extends RuntimeException{
	public AddressNotFoundException(){
		super("Address Not found");
	}
}
