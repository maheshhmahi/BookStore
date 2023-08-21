package com.bridgelabz.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BookstoreApplication {

	public static void main(String[] args) {
		
		ApplicationContext context=SpringApplication.run(BookstoreApplication.class, args);
		log.info("BookStore  app started in {} Environment",
				 context.getEnvironment().getProperty("environment"));
	}

}
