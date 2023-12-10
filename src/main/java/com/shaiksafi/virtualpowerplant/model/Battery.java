package com.shaiksafi.virtualpowerplant.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Battery represents a battery in the virtual power plant system.
@Entity
public class Battery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String postcode;
    private double wattCapacity;
    
    // Default constructor.
    public Battery() {}
    // Parameterized constructor for creating a battery.
	public Battery(String name, String postcode, double wattCapacity) {
		super();
		this.name = name;
		this.postcode = postcode;
		this.wattCapacity = wattCapacity;
	}
	
	// Getters and setters for battery properties.
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public double getWattCapacity() {
		return wattCapacity;
	}
	public void setWattCapacity(double wattCapacity) {
		this.wattCapacity = wattCapacity;
	}
	
	// ToString method for representing Battery object as a string.
	@Override
	public String toString() {
		return "Battery [id=" + id + ", name=" + name + ", postcode=" + postcode + ", wattCapacity=" + wattCapacity
				+ "]";
	}
}
