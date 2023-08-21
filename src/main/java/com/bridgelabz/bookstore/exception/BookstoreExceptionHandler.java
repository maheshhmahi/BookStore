package com.bridgelabz.bookstore.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bridgelabz.bookstore.dto.ResponseDTO;


@ControllerAdvice
public class BookstoreExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleMethodArgumentsNotValidException(MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errMsg = errorList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",errMsg);
		return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BookstoreException.class)
	public ResponseEntity<ResponseDTO> handleEmployeePayrollException(BookstoreException exception) {
		ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponseDTO> handleUserNotFoundException(UserNotFoundException exception) {
		ResponseDTO responseDTO = new ResponseDTO("User not found",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponseDTO> handleBookNotFoundException(BookNotFoundException exception) {
		ResponseDTO responseDTO = new ResponseDTO("Book not found",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserCartException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponseDTO> handleUserCartException(UserCartException exception) {
		ResponseDTO responseDTO = new ResponseDTO("User cart not found",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserWishlistException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResponseDTO> handleUserWishlistException(UserWishlistException exception) {
		ResponseDTO responseDTO = new ResponseDTO("User wishlist not found",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ResponseDTO> handleInvalidPasswordException(InvalidPasswordException exception) {
		ResponseDTO responseDTO = new ResponseDTO("invald password",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(UserAlreadyPresentException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ResponseDTO> handleUserAlreadyPresentException(UserAlreadyPresentException exception) {
		ResponseDTO responseDTO = new ResponseDTO("email already in use",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BookExistsException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ResponseEntity<ResponseDTO> handleBookExistsException(BookExistsException exception) {
		ResponseDTO responseDTO = new ResponseDTO("Book exists",exception.getMessage());
		return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.FORBIDDEN);
	}
	
}
