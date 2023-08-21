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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "order_entity")
public @Data class Order 
{

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_id", columnDefinition = "VARCHAR(255)")
    private UUID orderId;
    
    private int quantity;

    private LocalDate date;

    private long price;


    @ManyToOne
    @JsonIgnore
    private User user;


    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToMany
    private List<Book> books;


    public Order()
    {
        this.books =new ArrayList<>();
    }


}