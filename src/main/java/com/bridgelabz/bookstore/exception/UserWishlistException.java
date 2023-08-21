package com.bridgelabz.bookstore.exception;

public class UserWishlistException extends RuntimeException{
	
	public UserWishlistException(){
		super("User wishlist not found!");
	}

}
