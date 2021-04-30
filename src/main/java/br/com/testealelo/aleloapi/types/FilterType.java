package br.com.testealelo.aleloapi.types;

public enum FilterType {
	PLACA("plate"), 
	STATUS("status");

	private final String valor;
	
	FilterType(String valor){
		this.valor = valor;
	}
	
	public String getValor(){
		return valor;
	}
}
