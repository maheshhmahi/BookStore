package com.bridgelabz.bookstore.exception;


public class BookExistsException extends RuntimeException
{
    public BookExistsException(String message)
    {
        super(message);
    }
    
}
