package com.bridgelabz.bookstore.model;

import lombok.Data;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.JoinColumn;

import com.bridgelabz.bookstore.dto.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address")
public @Data class Address 
{
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "address_id", columnDefinition = "VARCHAR(255)")
    private UUID addressId;

    @NotNull(message = "city cannot not be null")
    private String city;

    @NotNull(message = "state cannot not be null")
    private String state;
    
    @NotNull(message = "address cannot not be null")
    private String address;

    @NotNull(message = "pin cannot not be null")
    private String pin;
    
    @NotNull(message = "type cannot not be null")
    private String type;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonIgnore
    private User user;
    
    
    public Address(AddressDTO addressDTO)
	{
    	this.updateAddress(addressDTO);
	}
    public Address()
	{

	}
	public void updateAddress(AddressDTO addressDTO) {
		this.city = addressDTO.getCity();
		this.state = addressDTO.getState();
		this.address = addressDTO.getAddress();
		this.pin = addressDTO.getPin();
		this.type = addressDTO.getType();
	}




}
