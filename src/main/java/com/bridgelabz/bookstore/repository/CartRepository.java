package com.bridgelabz.bookstore.repository;


import com.bridgelabz.bookstore.model.Cart;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> 
{
    
    
}
