package com.bridgelabz.bookstore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Address createAddress(AddressDTO addressDTO,String email) {
		Optional<User> user =  Optional.of(userRepository.findUserByEmail(email)
				.orElseThrow(()-> new UserNotFoundException(email)));
		Address address = null;
		address = new Address(addressDTO);
		address.setUser(user.get());
		return addressRepository.save(address);
	}
	@Override
	public List<Address> getAddressess() {
		
		return addressRepository.findAll();
	}
	@Override
	public void deleteAddress(UUID id) {
		Address address = addressRepository.getById(id);
		addressRepository.delete(address);
	}


}
