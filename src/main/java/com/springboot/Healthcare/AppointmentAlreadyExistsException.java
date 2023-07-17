package com.springboot.Healthcare;

public class AppointmentAlreadyExistsException extends Exception {
	public AppointmentAlreadyExistsException(String message) {
        super(message);
    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
