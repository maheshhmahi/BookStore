package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.User;


public interface AddressRepository extends JpaRepository<Address, UUID> {
	Optional<List<Address>> findAddressByUser(Optional<User> user);
}
