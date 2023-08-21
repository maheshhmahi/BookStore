package com.bridgelabz.bookstore.dto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.model.WishList;

import lombok.Data;

@Data
public class UserCombinedDTO
 {
	private UUID userId;
	private String fullName;
	
	private String email;
    
    private String mobileNumber;
    
    private String password; 
    private List<Address> addressList;

	private WishList wishList;

	private Cart cart;

	private String otp;
	
	public UserCombinedDTO(Optional<User> user,Optional<List<Address>> addresses) {
		this.update(user, addresses);
		
	}
	private void update(Optional<User> user, Optional<List<Address>> addresses) 
	{
		this.userId=user.get().getUserId();
		this.fullName = user.get().getFullName();
		this.email = user.get().getEmail();
		this.mobileNumber = user.get().getMobileNumber();
		this.password = user.get().getPassword();
		this.addressList = addresses.get();
		this.wishList=user.get().getWishList();
		this.cart=user.get().getCart();
		this.otp=user.get().getOtp();
	}
	
    
    
}