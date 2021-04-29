package br.com.testealelo.aleloapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name="Vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String plate;
    
    @NotBlank
    private String model;

    @NotBlank
	private String manufacturer;
	
    @NotBlank
	private String color;
	
    @NotNull
    @Type(type="yes_no")
	private boolean status;
	
	public Vehicle() {
		
	}

	public Vehicle(String plate, String model, String manufacturer, String color, Boolean status) {
		
		this.plate = plate;
		this.model = model;
		this.manufacturer = manufacturer;
		this.color = color;
		this.status = status;
							
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (plate == null) {
			if (other.plate != null)
				return false;
		} else if (!plate.equals(other.plate))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((plate == null) ? 0 : plate.hashCode());
		return result;
	}
    
}
