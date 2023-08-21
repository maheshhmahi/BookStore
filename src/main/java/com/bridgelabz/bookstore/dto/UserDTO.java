package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO {
	@NotEmpty(message="Employee name cannot be null")
	@Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message ="Name Invalid")
	private String fullName;
	
	@NotNull(message = "Email should not be Empty")
    @Email(message = "Email invalid")
    private String email;
    
    @NotEmpty(message = "Phone Number can not be null.")
    @Pattern(regexp = "^[6-9][0-9]{9}$")
    private String mobileNumber;
    
    @NotEmpty(message = "Password cannot be Empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password Invalid")
    private String password; 
}
