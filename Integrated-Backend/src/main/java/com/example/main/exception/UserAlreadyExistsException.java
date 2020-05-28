package com.example.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistsException(String message){
        super(message);
    }
	
	@Override
	public String toString() {
		String message=super.getMessage();
		return " "+message;
	}
}