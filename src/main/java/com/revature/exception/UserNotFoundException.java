package com.revature.exception;

public class UserNotFoundException extends Exception {
	public UserNotFoundException(String s) {
		super(s);
	}
	
	public UserNotFoundException() {
		this("User not found please try again.");
	}
}
