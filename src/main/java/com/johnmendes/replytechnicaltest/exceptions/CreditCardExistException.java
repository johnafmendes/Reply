package com.johnmendes.replytechnicaltest.exceptions;

public class CreditCardExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CreditCardExistException (String message){
		super(message);
	}
	
	public CreditCardExistException (String message, Throwable motivation){
		super(message, motivation);
	}

}
