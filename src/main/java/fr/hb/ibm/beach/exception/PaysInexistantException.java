package fr.hb.ibm.beach.exception;

public class PaysInexistantException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PaysInexistantException(String message) {
		super(message);
	}

}
