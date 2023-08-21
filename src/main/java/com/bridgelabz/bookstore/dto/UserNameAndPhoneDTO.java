package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserNameAndPhoneDTO {
	
	@NotEmpty(message="Employee name cannot be null")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="Name Invalid")
	private String fullName;
	
	@NotEmpty(message = "Phone Number can not be null.")
    @Pattern(regexp = "^[6-9][0-9]{9}$")
    private String mobileNumber;
}
