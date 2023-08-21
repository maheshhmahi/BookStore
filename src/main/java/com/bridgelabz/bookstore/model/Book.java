package com.bridgelabz.bookstore.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "book")
public @Data class Book {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "book_id", columnDefinition = "VARCHAR(255)")
    private UUID id;

	@Column(name = "book_name", nullable = false)
	private String bookName;

	@Column(name = "author_name", nullable = false)
	private String authorName;

	@Column(name = "book_image", nullable = false)
	private String bookImage;

	@Column(name = "actual_price")
	private long actualPrice;

	@Column(name = "offer_price")
	private long offerPrice;

	@Column(name = "number_of_books")
	private int numberOfBooks;

	@NotNull(message = "description cannot not be null")
	private String description;

	private double ratings;

	@Column(name = "number_of_ratings")
	private int numberOfRatings;
	
	@Column(name="create_time")
	private LocalDateTime createTime;

	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id")
    @JsonIgnore
    private List<WishList> wishLists;


	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    @JsonIgnore
    private List<Cart> carts;

	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnore
    private List<Order> orders;

	private int numberOfBooksSold;
	
	
	public Book() {

		this.wishLists = new ArrayList<WishList>();

	}

	public void updateBookData(BookDTO bookDTO) {

		this.bookName = bookDTO.getBookName();
		this.authorName = bookDTO.getAuthorName();
		this.bookImage = bookDTO.getBookImage();
		this.actualPrice = bookDTO.getActualPrice();
		this.offerPrice = bookDTO.getOfferPrice();
		this.numberOfBooks = bookDTO.getNumberOfBooks();
		this.description = bookDTO.getDescription();
		this.ratings = bookDTO.getRatings();
		this.numberOfRatings = bookDTO.getNumberOfRatings();
		this.createTime= LocalDateTime.now();
		this.numberOfBooksSold=bookDTO.getNumberOfBooks();

	}

	public Book(BookDTO bookDTO) 
	{

		this.updateBookData(bookDTO);
	}

}