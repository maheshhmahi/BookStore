package com.bridgelabz.bookstore.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.FeedBackDTO;
import com.bridgelabz.bookstore.exception.AddressNotFoundException;
import com.bridgelabz.bookstore.exception.UserNotFoundException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Feedback;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.FeedBackRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	FeedBackRepository feedBackRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Feedback addFeedback(FeedBackDTO feedBackDTO,String userMail,UUID bookId) 
	{
		User user = userRepository.findUserByEmail(userMail).orElseThrow(()-> new UserNotFoundException(userMail));
		UUID userId=user.getUserId();
		String userName=feedBackRepository.getUserName(userId);
		Feedback feedback= new Feedback(feedBackDTO,userId,bookId,userName);
		Book book = bookRepository.findById(bookId).orElseThrow(()->new AddressNotFoundException());
		double existingRatings=book.getRatings();

		List<Feedback> feedbacks =this.getFeedback(bookId);
		int numberOfPeopleGaveFeedback= feedbacks.size();
		double tempRatings=existingRatings*numberOfPeopleGaveFeedback;
		double newRatings =(tempRatings+feedBackDTO.getRating())/(numberOfPeopleGaveFeedback+1);

		book.setRatings(newRatings);
		book.setNumberOfRatings(numberOfPeopleGaveFeedback+1);

		bookRepository.save(book);

		return feedBackRepository.save(feedback);
	}
	@Override
	public List<Feedback> getFeedback(UUID bookId) {
		System.out.print(feedBackRepository.getAllFeedback(bookId));
		return feedBackRepository.getAllFeedback(bookId);
	}

}
