package com.shaiksafi.virtualpowerplant.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Battery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private String postcode;
    private double wattCapacity;
    
    public Battery() {}
	public Battery(String name, String postcode, double wattCapacity) {
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
	
	@Override
	public String toString() {
		return "Battery [id=" + id + ", name=" + name + ", postcode=" + postcode + ", wattCapacity=" + wattCapacity
				+ "]";
	}
}
