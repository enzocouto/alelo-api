package br.com.testealelo.aleloapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import br.com.testealelo.aleloapi.entity.Vehicle;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	
	public void deleteById(Long id);
	
	public List<Vehicle> findByStatus(Boolean status);
	
	public Optional<Vehicle> findFirstByPlate(String plate);
	
	
}
