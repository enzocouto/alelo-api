package br.com.testealelo.aleloapi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Vehicle {

    @Id
    private long id;
    
    private String plate;
    
    private String model;

	private String manufacturer;
	
	private String color;
	
	private boolean status;
	
    public Vehicle(long id, String plate, String model, String manufacturer, String color, boolean status) {
    	this.id= id;
    	this.plate = plate;
    	this.model = model;
    	this.manufacturer = manufacturer;
    	this.color = color;
    	this.status = status;
    }
    
}
