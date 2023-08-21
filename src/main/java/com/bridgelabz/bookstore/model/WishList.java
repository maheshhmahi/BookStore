package com.bridgelabz.bookstore.model;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "wishlist")
public @Data class WishList 
{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "wishlist_id", columnDefinition = "VARCHAR(255)")
	private UUID wishListId;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany
    private List<Book> books;

    private int quantity;


    public WishList()
    {
        this.books =new ArrayList<>();
    }
   

}
