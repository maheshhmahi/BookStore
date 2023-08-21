package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.MailDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.UserNameAndPhoneDTO;
import com.bridgelabz.bookstore.dto.UserCombinedDTO;
import com.bridgelabz.bookstore.dto.UserPasswordUpdateDTO;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model. User;
import com.bridgelabz.bookstore.service.MailService;
import com.bridgelabz.bookstore.service.UserService;
import com.bridgelabz.bookstore.utility.Authentication;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;


	@Autowired
	Authentication authentication;

	@Autowired
	MailService mailService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> addBookStoreData(
			@Valid @RequestBody UserDTO userDTO)
	{
		User user=null;
		user= userService.createUser(userDTO);
		ResponseDTO respDTO=new ResponseDTO("Created User Successfully",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<ResponseDTO> getUserByEmail(@RequestHeader (name="Authorization") String token)
	{
		
		UserCombinedDTO user=null;
		user = userService.getUserByEmail(token);
		ResponseDTO respDTO=new ResponseDTO("Get call for Email Success",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@GetMapping("/verify/otp/{email}/{otp}")
	public ResponseEntity<ResponseDTO> verifyOTP(@PathVariable("email") String email,@PathVariable("otp") String otp)
	{
		
		
		Boolean result = userService.verifyOTP(email,otp);
		ResponseDTO respDTO=new ResponseDTO("verification for otp",result);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getUserById(@PathVariable("id") UUID id)
	{
		UserCombinedDTO user=null;
		user = userService.getUserById(id);
		ResponseDTO respDTO=new ResponseDTO("Get call for Id Success",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@PatchMapping("/password/{email}")
	public ResponseEntity<ResponseDTO> updateUserPassword(@PathVariable("email") String email,
			@Valid @RequestBody UserPasswordUpdateDTO userPasswordUpdateDTO)
	{
		User user=null;

		user = userService.updateUserPassword(email,userPasswordUpdateDTO);
		ResponseDTO respDTO=new ResponseDTO("Update Password Success",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	@PostMapping("/forgotpassword/{email}")
	public ResponseEntity<ResponseDTO> SendMail(@PathVariable(name="email") String email)
	{
		String otp = userService.sendOtpMail(email);
		ResponseDTO respDTO=new ResponseDTO("Update Password Success",otp);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	
	@PatchMapping("/update/nameandphone")
	public ResponseEntity<ResponseDTO> updateUserAndPhone(@RequestHeader (name="Authorization") String token,
			@Valid @RequestBody UserNameAndPhoneDTO userNameAndPhoneDTO)
	{
		User user=null;

		user = userService.updateUserNameAndPassword(token,userNameAndPhoneDTO);
		ResponseDTO respDTO=new ResponseDTO("Updated Successfully",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<ResponseDTO> updateUser(@RequestHeader (name="Authorization") String token,
			@Valid @RequestBody UserDTO userDTO)
	{
		User user=null;
		user= userService.updateUser(token, userDTO);
		ResponseDTO respDTO=new ResponseDTO("Updated User Successfully",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@PatchMapping("/address")
	public ResponseEntity<ResponseDTO> updateUserAddress(@RequestHeader (name="Authorization") String token,
			@Valid @RequestBody AddressDTO addressDTO)
	{
		Optional<List<Address>> address=null;

		address = userService.updateUserAddress(token,addressDTO);
		ResponseDTO respDTO=new ResponseDTO("Update Address Success",address);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	@PatchMapping("/address/update/{id}")
	public ResponseEntity<ResponseDTO> updateUserAddressById(@RequestHeader (name="Authorization") String token,
			@PathVariable("id") UUID id,@Valid @RequestBody AddressDTO addressDTO)
	{
		Optional<Address> address=null;
		address = userService.updateUserAddressById(token,addressDTO,id);
		ResponseDTO respDTO=new ResponseDTO("Update Address by id Success",address);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
		
	}

	@PatchMapping("/address/{type}")
	public ResponseEntity<ResponseDTO> updateUserAddressByEmailType(@RequestHeader (name="Authorization") String token,
			@PathVariable("type") String type,@Valid @RequestBody AddressDTO addressDTO)
	{
		Optional<List<Address>> address=null;

		address = userService.updateUserAddressByEmailType(token,type,addressDTO);
		ResponseDTO respDTO=new ResponseDTO("Update Address Success",address);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@PutMapping("wishlist/{bookId}")
	public ResponseEntity<ResponseDTO> updateUserWishList(@RequestHeader (name="Authorization") String token,
														  @PathVariable("bookId") UUID bookId)
	{
		User user =null;

		user =userService.updateUserWishList(token, bookId);
		ResponseDTO respDTO=new ResponseDTO("Update Wishlist Success",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);

	}

	@PutMapping("cart/{bookId}")
	public ResponseEntity<ResponseDTO> updateUserCart(@RequestHeader (name="Authorization") String token,
														@PathVariable("bookId") UUID bookId)
	{
		User user =null;

		user =userService.updateUserCart(token, bookId);
		ResponseDTO respDTO=new ResponseDTO("Update Cart Success",user);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);

	}
	
	@DeleteMapping("/wishlist/{bookId}")
	public ResponseEntity<ResponseDTO> deleteWishlistByEmail(@RequestHeader (name="Authorization") String token,
														  @PathVariable("bookId") UUID bookId)
	{
		
		Boolean isDeleted = userService.deleteWishlistByEmail(token, bookId);
		ResponseDTO respDTO;
		if(isDeleted) {
			respDTO=new ResponseDTO("Delete wishlist Success ",authentication.authenticate(token.split(" ")[1]));
		}else {
			respDTO=new ResponseDTO("Error deleting user ",authentication.authenticate(token.split(" ")[1]));
		}
		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("/cart/{bookId}")
	public ResponseEntity<ResponseDTO> deleteCartByEmail(@RequestHeader (name="Authorization") String token,
														  @PathVariable("bookId") UUID bookId)
	{
		
		Boolean isDeleted = userService.deleteCartByEmail(token, bookId);
		ResponseDTO respDTO;
		if(isDeleted) {
			respDTO=new ResponseDTO("Delete Cart Success ",authentication.authenticate(token.split(" ")[1]));
		}else {
			respDTO=new ResponseDTO("Error deleting user ",authentication.authenticate(token.split(" ")[1]));
		}
		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@PostMapping("/order/{cartId}")
	public ResponseEntity<ResponseDTO> updateUserOrder(@RequestHeader (name="Authorization") String token,
													  @PathVariable("cartId") UUID cartId)
														  
	{
		
		UUID orderId =userService.updateUserOrder(token, cartId);
		ResponseDTO respDTO=new ResponseDTO("Update Order Success",orderId);		
		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}

	@GetMapping("/order")
	public ResponseEntity<ResponseDTO> getUserOrders(@RequestHeader (name="Authorization") String token)
														  
	{

		List<Order> orders =userService.getUserOrders(token);
		ResponseDTO respDTO=new ResponseDTO("Get call for users orders Success",orders);		
		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
	@GetMapping("/order/{bookId}")
	public ResponseEntity<ResponseDTO> getUserOrderedBook(@RequestHeader (name="Authorization") String token,@PathVariable("bookId") UUID bookId)													  
	{
		
		Boolean bookOrderd=userService.getUserOrderedBook(token, bookId);
		ResponseDTO respDTO=new ResponseDTO("Get call for users orders Success",bookOrderd);		
		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);

	}

	@PostMapping("/login")
	public  ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO)
	{

		String token =userService.login(loginDTO);
		ResponseDTO respDTO=new ResponseDTO("User authentication  successful",token);		
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);

	}
	
}
