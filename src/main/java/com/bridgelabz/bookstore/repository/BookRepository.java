package com.bridgelabz.bookstore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstore.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
	@Query(value="select * from book order by book_name asc",nativeQuery = true)
	List<Book> getBooksBySortedName();
	
	@Query(value="select * from book order by actual_price asc",nativeQuery = true)
	List<Book> getBooksBySortedPrice();
	
	@Query(value="select * from book order by ratings asc",nativeQuery = true)
	List<Book> getBooksBySortedRatings();
	
	@Query(value="select * from book order by create_time desc",nativeQuery = true)
	List<Book> getBooksBySortedTime();
	
	@Query(value = "select * from book b where lower(b.book_name) like %:name% or b.author_name like %:name%",nativeQuery = true)
	List<Book> getBooksBySearch(String name);
	
	//Book findById(UUID id);
	
}
