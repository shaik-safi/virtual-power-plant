package com.shaiksafi.virtualpowerplant.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Represents a battery in the virtual power plant system.

@Entity
public class Battery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private int postcode;
    private double wattCapacity;
    
    public Battery() {}
	public Battery(String name, int postcode, double wattCapacity) {
		super();
		this.name = name;
		this.postcode = postcode;
		this.wattCapacity = wattCapacity;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public double getWattCapacity() {
		return wattCapacity;
	}
	public void setWattCapacity(double wattCapacity) {
		this.wattCapacity = wattCapacity;
	}
	
	@Override
	public String toString() {
		return "Battery [id=" + id + ", name=" + name + ", postcode=" + postcode + ", wattCapacity=" + wattCapacity
				+ "]";
	}
}
