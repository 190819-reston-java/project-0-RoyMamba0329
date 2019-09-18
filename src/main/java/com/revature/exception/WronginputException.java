package com.revature.exception;

public class WronginputException extends Exception {

	public WronginputException() {
		this("Wrong input please try again!");
	}
	public WronginputException(String s) {
		super(s);
	}
}
