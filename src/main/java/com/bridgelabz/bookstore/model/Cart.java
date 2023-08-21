package com.bridgelabz.bookstore.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cart")
public @Data class Cart 
{

 

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cart_id", columnDefinition = "VARCHAR(255)")
	private UUID cartId;

    
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany
    private List<Book> books;

    private int quantity;


    public Cart()
    {
        this.books =new ArrayList<>();

    }

    public Order UpdateCartToOrder( )
    {
        
        Order order = new Order();
         order.setBooks(this.books);
         order.setQuantity(this.quantity);
         order.setDate(LocalDate.now());
         long price=0;
        for (Book book : books) 
        {
            price+=book.getOfferPrice();
            
        }
         order.setPrice(price);



        this.books=new ArrayList<>();
        this.quantity=0;

        return order;

    }
    
}