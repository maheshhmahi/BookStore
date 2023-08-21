package com.bridgelabz.bookstore.service;

import java.util.List;
import java.util.UUID;


import com.bridgelabz.bookstore.dto.FeedBackDTO;
import com.bridgelabz.bookstore.model.Feedback;

public interface FeedBackService {

	Feedback addFeedback(FeedBackDTO feedBackDTO,String userMail,UUID bookId);
	List<Feedback> getFeedback(UUID bookId);
}
