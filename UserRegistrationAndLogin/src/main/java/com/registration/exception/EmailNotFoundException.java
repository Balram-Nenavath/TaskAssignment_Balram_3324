package com.registration.exception;

public class EmailNotFoundException extends RuntimeException {

	private String message;

	public EmailNotFoundException() {}

	public EmailNotFoundException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}