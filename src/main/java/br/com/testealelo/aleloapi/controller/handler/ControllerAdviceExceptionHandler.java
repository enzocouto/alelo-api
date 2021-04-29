package br.com.testealelo.aleloapi.controller.handler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.testealelo.aleloapi.service.exception.AtualizacaoPlacaDiferenteException;
import br.com.testealelo.aleloapi.service.exception.ImpossivelAtualizarVeiculoException;
import br.com.testealelo.aleloapi.service.exception.ImpossivelExcluirVeiculoException;
import br.com.testealelo.aleloapi.service.exception.OcorreuErroSalvarVeiculoException;
import br.com.testealelo.aleloapi.service.exception.VeiculoInexistenteException;
import br.com.testealelo.aleloapi.service.exception.VeiculoJaExisteException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	
	@ExceptionHandler(AtualizacaoPlacaDiferenteException.class)
	public ResponseEntity<String> handleAtualizacaoPlacaDiferenteException(AtualizacaoPlacaDiferenteException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		
		return ResponseEntity.badRequest().body("Identificador não existente na Base de Dados");
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		
		return ResponseEntity.badRequest().body("Ocorreu um erro ao salvar Veículo.");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ObjectError objectError = e.getAllErrors().get(0);
		return ResponseEntity.badRequest().body("Campo "+objectError.getDefaultMessage());
	}
	
	@ExceptionHandler(ImpossivelAtualizarVeiculoException.class)
	public ResponseEntity<String> handleImpossivelAtualizarEntidadeException(ImpossivelAtualizarVeiculoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(ImpossivelExcluirVeiculoException.class)
	public ResponseEntity<String> handleImpossivelExcluirEntidadeException(ImpossivelExcluirVeiculoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(OcorreuErroSalvarVeiculoException.class)
	public ResponseEntity<String> handleOcorreuErroSalvarVeiculoException(OcorreuErroSalvarVeiculoException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(VeiculoJaExisteException.class)
	public ResponseEntity<String> handleVeiculoJaExisteException(VeiculoJaExisteException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(VeiculoInexistenteException.class)
	public ResponseEntity<String> handleVeiculoInexistenteException(VeiculoInexistenteException e) {
		
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
