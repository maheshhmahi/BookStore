package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.service.AddressService;
import com.bridgelabz.bookstore.utility.Authentication;

@CrossOrigin("*")
@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	AddressService addressService;

	@Autowired
	Authentication authentication;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addBookStoreData(@RequestHeader (name="Authorization") String token,
			@Valid @RequestBody AddressDTO addressDTO)
	{
		token=token.split(" ")[1];
		String email=authentication.authenticate(token);

		Address address=null;
		address= addressService.createAddress(addressDTO,email);
		ResponseDTO respDTO=new ResponseDTO("Created Address Successfully",address);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	@GetMapping("")
	public ResponseEntity<ResponseDTO> getAddressess(){
		List<Address> address=null;
		address = addressService.getAddressess();
		ResponseDTO respDTO=new ResponseDTO("Get Address for Email Success",address);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteAddress(@PathVariable("id") UUID id){
		addressService.deleteAddress(id);
		ResponseDTO respDTO = new ResponseDTO("Deleted Address Successfully","Deleted Id:"+id);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	
	
}
