package com.bridgelabz.bookstore.service;


import java.util.List;
import java.util.UUID;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.model.Address;

public interface AddressService{

	Address createAddress(AddressDTO addressDTO,String email);

	List<Address> getAddressess();

	void deleteAddress(UUID id);

	
}
