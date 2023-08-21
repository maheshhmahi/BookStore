package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstore.model.Feedback;
@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, UUID>{
	@Query(value="select full_name from user where user_id = :userId",nativeQuery = true)
	String getUserName(UUID userId);
	
	@Query(value="select * from feedback where book_id = :bookId",nativeQuery = true)
	List<Feedback> getAllFeedback(UUID bookId);
	
	
}
