package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

public @Data class FeedBackDTO {
	@NotEmpty(message = "review cannot bw null")
    private String review;
	
	@NotEmpty(message = "rating cannot be null")
    private double rating;
}
