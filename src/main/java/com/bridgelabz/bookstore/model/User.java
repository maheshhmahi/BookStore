package com.bridgelabz.bookstore.model;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bridgelabz.bookstore.dto.UserDTO;

import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User 
{
   
    
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
	private UUID userId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private String password;

    private String otp;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "wishlist_id", referencedColumnName = "wishlist_id")
    private WishList wishList;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

	public void update(UserDTO userDTO) 
    {
		
		this.fullName = userDTO.getFullName();
		this.email = userDTO.getEmail().toLowerCase();
		this.mobileNumber = userDTO.getMobileNumber();
		this.password = userDTO.getPassword();
        
	}
    public User(UserDTO userDTO) 
    {
    	this.update(userDTO);
        this.wishList = new WishList();
        this.cart = new Cart();


    }
    public User() 
    {
    	
    }
    
}
