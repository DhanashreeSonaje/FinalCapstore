package com.example.main.exception;

public class ValidAmountException extends Exception{

	private static final long serialVersionUID = 1L;

	public ValidAmountException(String message) {
		super(message);
	}
	
	@Override
	public String toString() {
		String message=super.getMessage();
		return " "+message;
	}
}


