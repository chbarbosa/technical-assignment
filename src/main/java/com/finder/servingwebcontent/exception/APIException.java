package com.finder.servingwebcontent.exception;

public class APIException extends Exception {


	/**
	 * serial uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message error message.
	 * @param e error cause
	 */
	public APIException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Default constructor.
	 * @param message error message.
	 */
	public APIException(String message) {
		super(message);
	}

}
