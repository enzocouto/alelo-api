package br.com.testealelo.aleloapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.com.testealelo.aleloapi.config.H2JpaConfig;
import br.com.testealelo.aleloapi.entity.Vehicle;
import br.com.testealelo.aleloapi.repository.VehicleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { H2JpaConfig.class }, 
  loader = AnnotationConfigContextLoader.class)
@Transactional
public class InMemoryDBTest {
	
	    @Resource
	    private VehicleRepository vehicleRepository;
	    
	    @Test
	    public void givenStudent_whenSave_thenGetOk() {
	    	Vehicle vehicle = new Vehicle(1L, "ABC1234","Class C 1.1 Avantgarde Turbo Flex","Mercedes-Benz","black",true);
	    	vehicleRepository.save(vehicle);
	        
	        Optional<Vehicle> optVehicle2 = vehicleRepository.findById(1L);
	        assertEquals("ABC1234", optVehicle2.get().getPlate());
	    }
}
