package com.saltlux.mysite.exception;

public class UserRepositoryException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserRepositoryException() {
		super("Throwing UserRepositoryException");
	}
	
	public UserRepositoryException(String message) {
		super(message);
	}
}
