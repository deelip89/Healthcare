package com.springboot.Healthcare;

public class AppointmentAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentAlreadyExistsException(String message) {
        super(message);
    }

}
