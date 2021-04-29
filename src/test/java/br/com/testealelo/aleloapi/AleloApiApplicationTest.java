package br.com.testealelo.aleloapi;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.testealelo.aleloapi.entity.Vehicle;
import br.com.testealelo.aleloapi.repository.VehicleRepository;
import br.com.testealelo.aleloapi.service.VehicleService;
import br.com.testealelo.aleloapi.service.exception.AtualizacaoPlacaDiferenteException;
import br.com.testealelo.aleloapi.service.exception.VeiculoJaExisteException;

@RunWith(JUnit4.class)
@SpringBootTest(classes = AleloApiApplication.class)
public class AleloApiApplicationTest {
	
	    @Autowired
	    private VehicleService vehicleService;
	    
	    @Autowired
	    private VehicleRepository vehicleRepository;
	    
	    Vehicle vehicle;
	  
		 @Nested
		    class TestNest{
		    
		       @BeforeEach
		       public void init() {
		    	   vehicleRepository.deleteAll();
		    	   vehicle = new Vehicle("ABC1234","Class C 1.1 Avantgarde Turbo Flex","Mercedes-Benz","black",true);
		       }
		    
		       @Test
			    public void salvarVehicleComSucesso() {		    			    				    	
			    	vehicleService.salvarVehicle(vehicle);		        
			        Vehicle vehicle2 = vehicleService.buscarVeiculoPorPlaca("ABC1234");
			        assertEquals("ABC1234", vehicle2.getPlate());
			    }
			    
				@Test
			    public void salvarVehicleExistenteComErro() {						    	
			    	vehicleService.salvarVehicle(vehicle);
			    	Throwable exception = assertThrows(VeiculoJaExisteException.class, () -> vehicleService.salvarVehicle(vehicle));
			    	assertEquals("Nao é possível incluir veículo.Placa já existente.", exception.getMessage());   
			    }
				
				
				@Test
			    public void salvarVehicleSemModeloPreenchidoComErro() {				
			    	Vehicle vehicleSemModelo =  vehicle;
			    	vehicleSemModelo.setModel("");
			    	assertThrows(ConstraintViolationException.class, () -> vehicleService.salvarVehicle(vehicleSemModelo));
			    	 
			    }
				
				
				@Test
			    public void atualizarVeiculoPlacaDiferente() {	
					vehicleService.salvarVehicle(vehicle);
					Vehicle vehiclePlacaDiferenteAtualizado =  vehicle;
					vehiclePlacaDiferenteAtualizado.setPlate("AAA22334");
			    	assertThrows(AtualizacaoPlacaDiferenteException.class, () -> vehicleService.editVehicle(vehiclePlacaDiferenteAtualizado));
			    	 
			    }
				
				 
		     }
  
}