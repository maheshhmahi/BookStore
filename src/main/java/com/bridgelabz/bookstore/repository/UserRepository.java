package com.bridgelabz.bookstore.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findUserByEmail(String email);
}	
