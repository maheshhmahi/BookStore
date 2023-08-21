package com.bridgelabz.bookstore.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.bridgelabz.bookstore.dto.FeedBackDTO;

import lombok.Data;


@Entity
@Table(name = "feedback")
public @Data class Feedback 
{
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "feedback_id", columnDefinition = "VARCHAR(255)")
	private UUID feedbackId;

    @Column(name = "book_id" , columnDefinition = "VARCHAR(255)",nullable=false)
    private UUID bookId;

    @Column(name = "user_id" , columnDefinition = "VARCHAR(255)",nullable=false)
    private UUID userId;

    @NotNull
    private double rating;
    
    @NotNull(message = "Review cannot not be null")
    private String review;
    
    @Column(name = "user_name",nullable=false)
    private String userName;
public Feedback() {
	
}
    public Feedback(FeedBackDTO feedBackDTO,UUID userId,UUID bookId,String userName) 
    {
    	
    	this.updateOrderData(feedBackDTO,userId,bookId,userName);
    }

	private void updateOrderData(FeedBackDTO feedBackDTO,UUID userId,UUID bookId,String userName) {
		this.review=feedBackDTO.getReview();
		this.rating=feedBackDTO.getRating();
		this.userId=userId;
		this.bookId=bookId;
		this.userName=userName;
	}

}
