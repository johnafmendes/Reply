package com.johnmendes.replytechnicaltest.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.johnmendes.replytechnicaltest.entity.ErrorDetail;
import com.johnmendes.replytechnicaltest.exceptions.UserExistException;
import com.johnmendes.replytechnicaltest.exceptions.CreditCardExistException;
import com.johnmendes.replytechnicaltest.exceptions.UserNotFoundException;

@ControllerAdvice //Used to catch all exception of the code
public class ResourceExceptionHandler {

	@ExceptionHandler(UserExistException.class)
	public ResponseEntity<ErrorDetail> handleUserExistException(
			UserExistException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(409l);
		error.setTitle("User already exist.");
		error.setMessageDeveloper("http://error.johnmendes.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleUserNotFoundException(
			UserNotFoundException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(404l);
		error.setTitle("The User cannot be found.");
		error.setMessageDeveloper("http://error.johnmendes.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(CreditCardExistException.class)
	public ResponseEntity<ErrorDetail> handleCreditCardExistException(
			CreditCardExistException e, HttpServletRequest request){
		ErrorDetail error = new ErrorDetail();
		error.setStatus(409l);
		error.setTitle("Credit Card already exist.");
		error.setMessageDeveloper("http://error.johnmendes.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
}
