package com.johnmendes.replytechnicaltest.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public UserNotFoundException (String message){
		super(message);
	}
	
	public UserNotFoundException (String message, Throwable motivation){
		super(message, motivation);
	}
}
