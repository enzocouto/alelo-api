package br.com.testealelo.aleloapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.testealelo.aleloapi.service.VehicleService;
import br.com.testealelo.aleloapi.service.exception.ImpossivelAtualizarEntidadeException;
import br.com.testealelo.aleloapi.service.exception.ImpossivelExcluirEntidadeException;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	VehicleService vehicleservice;
	
	@GetMapping
	public String listVehiclesPagined(Pageable pageable) {
		return  "Page Number: "+pageable.getPageNumber() + " Page Size: "+pageable.getPageSize();
	}
	
	
	@GetMapping(params = "filter")
	public String listVehiclesByPlate(@RequestParam("filter") String plate) {
		return "Plate:"+plate;
	}
	
	/* Estou utilizando o Verbo PATCH para não ocorrer erro de mapeamento ambiguo
	 * já que o contrato tem o mesmo nome de parametro 'filter' tanto para a consulta por placa e consulta por status
	 * Para respeitar o contrato minha decisão foi utilizar essa foi a solução, mas penso que os nomes dos parametros 
	 * deveriam ser especifico para cada um dos recursos*/
	@PatchMapping(params = "filter")
	public String listVehiclesByStatus(@RequestParam("filter") boolean status) {
		return "Status:"+ status;
	}
	
	@GetMapping("/{id}")
	public String findVehicleById(@PathVariable Integer id) {
		return "Consulta por Id do Veiculo:"+id;
	}
	
	@PostMapping("/{id}")
	public String createVehicle(@PathVariable("id") Integer id) {
		return "Veiculo criado:"+id;
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<?> upDateVehicle(@PathVariable("id") Integer id) {
		try {
			vehicleservice.editVehicle(id);
			return ResponseEntity.ok().build();
		}catch(ImpossivelAtualizarEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) {
		
		try {
			vehicleservice.excluir(id);
			return ResponseEntity.ok().build();
		}catch(ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
