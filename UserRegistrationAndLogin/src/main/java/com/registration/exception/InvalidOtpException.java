package com.registration.exception;

public class InvalidOtpException
extends RuntimeException {

	private String message;

	public InvalidOtpException() {}

	public InvalidOtpException(String msg)
	{
		super(msg);
		this.message = msg;
	}
}