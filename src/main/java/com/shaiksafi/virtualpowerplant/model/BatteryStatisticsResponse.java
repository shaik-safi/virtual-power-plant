package com.shaiksafi.virtualpowerplant.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Represents response of statistics of batteries

@Entity
public class BatteryStatisticsResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private double total;
    private double average;
    private List<String> names;
    
    public BatteryStatisticsResponse() {}
    		
	public BatteryStatisticsResponse(double total, double average, List<String> names) {
		super();
		this.total = total;
		this.average = average;
		this.names = names;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public String toString() {
		return "BatteryStatisticsResponse [total=" + total + ", average="
				+ average + ", names=" + names + "]";
	}
    
    
}
