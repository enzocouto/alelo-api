package br.com.testealelo.aleloapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.testealelo.aleloapi.entity.Vehicle;
import br.com.testealelo.aleloapi.service.VehicleService;


@RestController
@RequestMapping("/vehicles")
public class VehicleController {


	@Autowired
	VehicleService vehicleservice;
	
	@GetMapping
	public Page<Vehicle> listVehiclesPagined(Pageable pageable) {
		//return  "Page Number: "+pageable.getPageNumber() + " Page Size: "+pageable.getPageSize();
		return vehicleservice.filtrar(pageable);
		
	}
	
	
	@GetMapping(params = "filter")
	public Vehicle listVehiclesByPlate(@RequestParam("filter") String plate) {
		return vehicleservice.buscarVeiculoPorPlaca(plate);
	}
	
	/* Estou utilizando o Verbo PATCH para não ocorrer erro de mapeamento ambiguo
	 * já que o contrato tem o mesmo nome de parametro 'filter' tanto para a consulta por placa e consulta por status
	 * Para respeitar o contrato minha decisão foi utilizar essa foi a solução, mas penso que os nomes dos parametros 
	 * deveriam ser especifico para cada um dos recursos*/
	@PatchMapping(params = "filter")
	public  List<Vehicle> listVehiclesByStatus(@RequestParam("filter") Boolean status) {		
		return vehicleservice.buscarVeiculoPorStatus(status);
	}
	
	@GetMapping("/{id}")
	public Vehicle findVehicleById(@PathVariable Long id) {
		return vehicleservice.findById(id);
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<?> createVehicle(@Valid @RequestBody Vehicle vehicle) {
	    vehicleservice.salvarVehicle(vehicle);
		return ResponseEntity.ok().body("Veículo salvo com sucesso!");
	}
	
	@PutMapping
	public @ResponseBody ResponseEntity<?> upDateVehicle(@Valid @RequestBody Vehicle vehicle) {
		vehicleservice.editVehicle(vehicle);
		return ResponseEntity.ok().body("Veículo atualizado com sucesso!");
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> deleteVehicle(@PathVariable("id") Long id) {	
		vehicleservice.excluir(id);
		return ResponseEntity.ok().build();
	}
	
}
