package br.com.testealelo.aleloapi.service.exception;

public class VeiculoInexistenteException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VeiculoInexistenteException(String msg) {
		super(msg);
	}

}
