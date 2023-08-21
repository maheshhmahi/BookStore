package com.bridgelabz.bookstore.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.UserNameAndPhoneDTO;
import com.bridgelabz.bookstore.dto.UserCombinedDTO;
import com.bridgelabz.bookstore.dto.UserPasswordUpdateDTO;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.User;

public interface UserService {
	User createUser(UserDTO userDTO);
	User updateUserPassword(String email, UserPasswordUpdateDTO userPasswordUpdateDTO);
	UserCombinedDTO getUserByEmail(String email);
	Optional<List<Address>> updateUserAddress(String email, AddressDTO addressDTO);
	UserCombinedDTO getUserById(UUID id);
	User updateUserWishList(String email, UUID bookId) ;
	User updateUserCart(String email, UUID bookId);
	Optional<List<Address>> updateUserAddressByEmailType(String email, String type,AddressDTO addressDTO);
	Boolean deleteWishlistByEmail(String email, UUID bookId);
	Boolean deleteCartByEmail(String email, UUID bookId);
    UUID updateUserOrder(String email, UUID cartId);
	List<Order> getUserOrders(String email);
	Boolean getUserOrderedBook(String email,UUID bookId);
	String login(LoginDTO loginDTO);
	Optional<Address> updateUserAddressById(String token, @Valid AddressDTO addressDTO,UUID id);
	User updateUserNameAndPassword(String token, @Valid UserNameAndPhoneDTO userNameAndPhoneDTO);
	User updateUser(String token, @Valid UserDTO userDTO);
	String sendOtpMail(String email);
	Boolean verifyOTP(String email,String otp);




}