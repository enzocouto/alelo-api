package br.com.testealelo.aleloapi.types;

public enum ErrosTypes {
IMPOSSIVEL_EXCLUIR_VEICULO("Impossivel excluir veículo."), 
VEICULO_INEXISTENTE("Não existe o veículo com a identificação consultada."), 
PLACA_JA_CADASTRADA("Nao é possível incluir veículo.Placa já existente."), 
ATUALIZACA_PLACA_DIFERENTE("Não é permitido atualizar a placa do veiculo."), 
PLACA_INEXISTENTE("Não existe veículo cadastrado com a placa consultada.");

private final String valor;
ErrosTypes(String erro){
valor = erro;
}
public String getValor(){
return valor;
}
}
