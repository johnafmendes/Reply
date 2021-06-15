package com.johnmendes.replytechnicaltest.exceptions;

public class UserExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1869300553614629710L;

	public UserExistException (String message){
		super(message);
	}
	
	public UserExistException (String message, Throwable motivation){
		super(message, motivation);
	}
}
