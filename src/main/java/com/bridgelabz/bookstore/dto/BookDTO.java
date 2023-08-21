package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;
@Data
public  class BookDTO {
	@NotEmpty(message = "Book name cannot be empty")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Book Name Invalid")
	private String bookName;

	@NotEmpty(message = "Author name cannot not be empty")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Author name Invalid")
	private String authorName;

	@NotEmpty(message = "BookImage cannot be empty")
	private String bookImage;

	@NotEmpty(message = "Actual Price cannot be empty")
	private long actualPrice;

	@NotEmpty(message = "Offer Price cannot be empty")
	private long offerPrice;

	@NotEmpty(message = "Number of books cannot be empty")
	private int numberOfBooks;

	@NotEmpty(message = "Description cannot be empty")
	private String description;

	@NotEmpty(message = "Ratings cannot be empty")
	private long ratings;

	@NotEmpty(message = "Number Of Ratings cannot be empty")
	private int numberOfRatings;




}