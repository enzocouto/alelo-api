package br.com.testealelo.aleloapi.service;


import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.testealelo.aleloapi.controller.VehicleController;
import br.com.testealelo.aleloapi.entity.Vehicle;
import br.com.testealelo.aleloapi.repository.VehicleRepository;
import br.com.testealelo.aleloapi.service.exception.AtualizacaoPlacaDiferenteException;
import br.com.testealelo.aleloapi.service.exception.ImpossivelExcluirVeiculoException;
import br.com.testealelo.aleloapi.service.exception.VeiculoInexistenteException;
import br.com.testealelo.aleloapi.service.exception.VeiculoJaExisteException;
import br.com.testealelo.aleloapi.types.ErrosTypes;


@Service
public class VehicleService {

	
	@Autowired
	VehicleRepository vehicleRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	public void excluir(Long id) {
		
		try {				
			vehicleRepository.deleteById(id);
			vehicleRepository.flush();	
		}catch(PersistenceException e) {
			logger.info(ErrosTypes.IMPOSSIVEL_EXCLUIR_VEICULO.getValor() + "id -> "+ id );
			throw new ImpossivelExcluirVeiculoException(ErrosTypes.IMPOSSIVEL_EXCLUIR_VEICULO.getValor());
	    }
		
	}
	public List<Vehicle> findAllVehicle(){
		return vehicleRepository.findAll();
	}
	
	public Vehicle findById(Long id){
		Vehicle vehicle = null;
		Optional<Vehicle> optFindById = vehicleRepository.findById(id);
		if(!optFindById.isPresent()) {
			logger.info(ErrosTypes.VEICULO_INEXISTENTE.getValor() + "id -> "+ id);
			throw new VeiculoInexistenteException(ErrosTypes.VEICULO_INEXISTENTE.getValor());
			
		}
		return vehicle;
	}
	
	public List<Vehicle> buscarVeiculoPorStatus(Boolean status){
		return vehicleRepository.findByStatus(status);
	}
	
	public Vehicle buscarVeiculoPorPlaca(String plate){
		Vehicle vehicle = null;
		Optional<Vehicle> optFindByPlate = vehicleRepository.findFirstByPlate(plate);
		if(optFindByPlate.isPresent()) {
			vehicle = optFindByPlate.get();
		}else {
			logger.info(ErrosTypes.PLACA_INEXISTENTE.getValor() + " - Placa ->"+  plate);
			throw new VeiculoInexistenteException(ErrosTypes.PLACA_INEXISTENTE.getValor());
		}
		return vehicle;
		
	}
	

	public void editVehicle(Vehicle vehicle) {
		Optional<Vehicle> optFindByPlate = vehicleRepository.findById(vehicle.getId());
		if(optFindByPlate.isPresent()) {
			Vehicle vehicleConsulta = optFindByPlate.get();			
			if(!vehicleConsulta.getPlate().equals(vehicle.getPlate())) {
				logger.info(ErrosTypes.ATUALIZACA_PLACA_DIFERENTE.getValor() + " - Placa ->"+ vehicle.getPlate());
				throw new AtualizacaoPlacaDiferenteException(ErrosTypes.ATUALIZACA_PLACA_DIFERENTE.getValor());
			}
			vehicleRepository.save(vehicle);
		}
		
		
	}

	@Transactional
	public void salvarVehicle(Vehicle vehicle) {
		Optional<Vehicle> optFindByPlate = vehicleRepository.findFirstByPlate(vehicle.getPlate());
		if(optFindByPlate.isPresent()) {
			logger.info(ErrosTypes.PLACA_JA_CADASTRADA.getValor() + " - Placa ->"+ vehicle.getPlate());
			throw new VeiculoJaExisteException(ErrosTypes.PLACA_JA_CADASTRADA.getValor());
		}
		vehicleRepository.save(vehicle);
			
	}
	public Page<Vehicle> filtrar(Pageable pageable) {
		//Como 	
		return vehicleRepository.findAll(pageable);
	}

    public Vehicle buscarVeiculoPorTipoFiltro(String filter, String type) {
		if("plate".equals(type)){
			return buscarVeiculoPorPlaca(filter);
		}
		return null;
    }
}
