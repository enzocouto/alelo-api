package br.com.testealelo.aleloapi.service.exception;

public class VeiculoJaExisteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VeiculoJaExisteException(String msg) {
		super(msg);
	}

}
