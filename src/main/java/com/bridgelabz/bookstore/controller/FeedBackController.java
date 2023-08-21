package com.bridgelabz.bookstore.controller;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.FeedBackDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.model.Feedback;
import com.bridgelabz.bookstore.service.FeedBackService;
import com.bridgelabz.bookstore.utility.Authentication;

@CrossOrigin("*")
@RestController
@RequestMapping("/feedback")
public class FeedBackController {
	@Autowired
	FeedBackService feedBackService;
	
	@Autowired
	Authentication authentication;

	@PostMapping("/create/{bookId}")
	public ResponseEntity<ResponseDTO> addFeedBack(@RequestHeader (name="Authorization") String token,@PathVariable("bookId") UUID bookId,@RequestBody FeedBackDTO feedBackDTO)
	{
		token=token.split(" ")[1];
		String usermail=authentication.authenticate(token);

		Feedback feedback=feedBackService.addFeedback(feedBackDTO,usermail,bookId);
		ResponseDTO respDTO=new ResponseDTO("Created Feedback Successfully",feedback);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	@GetMapping("/{bookId}")
	public ResponseEntity<ResponseDTO> addFeedBack(@PathVariable("bookId") UUID bookId)
	{
		List<Feedback> feedbackList=feedBackService.getFeedback(bookId);
		ResponseDTO respDTO=new ResponseDTO("Get call for feedback Successy",feedbackList);
		return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
	}
	
}
