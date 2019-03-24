package com.invillia.acme.service.exceptions;

public class GenericException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public GenericException(String msg) {
		super(msg);
	}
	
	public GenericException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
