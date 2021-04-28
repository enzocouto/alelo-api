package br.com.testealelo.aleloapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.testealelo.aleloapi.entity.Vehicle;



public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	
}
