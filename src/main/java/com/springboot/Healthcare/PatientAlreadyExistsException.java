package com.springboot.Healthcare;

public class PatientAlreadyExistsException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientAlreadyExistsException(String message) {
        super(message);
    }

}
