package com.bridgelabz.bookstore.dto;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public  class AddressDTO 
{

    @NotEmpty(message = "City cannot be null")
    @Pattern(regexp = "^[A-Z][a-z]{2,}+( +[A-Z][a-z]{2,}+)*$",message = "City Invalid")
    private String city;
    
    @NotEmpty(message = "State cannot be null")
    @Pattern(regexp = "^[A-Z][a-z]{2,}+( +[A-Z][a-z]{2,}+)*$",message = "State Invalid")
    private String state;

    @NotEmpty(message = "Address cannot be null")
    @Pattern(regexp = "(?!^\\d+$)^[A-Z,a-z,0-9, ()#-]{3,}$",message = "Address Invalid")
    private String address;

    @NotEmpty(message = "Pin cannot be null")
    @Pattern(regexp = "[0-9]{3}[ ]?[0-9]{3}$",message = "Zip Invalid")
    private String pin;

    @NotEmpty(message = "address type cannot be null")
    private String type;

	public AddressDTO( String city, String state, String address, String pin,String type)
	{
		this.city = city;
		this.state = state;
		this.address = address;
		this.pin = pin;
		this.type = type;
	}
    
    

}
