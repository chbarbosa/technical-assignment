package com.finder.servingwebcontent.exception;

/**
 * Represents the file access error.
 * @author chbarbosa
 *
 */
public class FileAccessException extends Exception {

	/**
	 * serial uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message error message.
	 * @param e error cause
	 */
	public FileAccessException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Default constructor.
	 * @param message error message.
	 */
	public FileAccessException(String message) {
		super(message);
	}

	/**
	 * Default constructor.
	 * @param e error cause
	 */
	public FileAccessException(Exception e) {
		super(e);
	}

}
