package com.shaiksafi.virtualpowerplant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shaiksafi.virtualpowerplant.model.Battery;

//BatteryRepository provides CRUD operations for Battery entities.
@Repository
public interface BatteryRepository extends CrudRepository<Battery, Long> {
	
	// Find batteries within a specified postcode range.
	List<Battery> findByPostcodeBetween(String startPostcode, String endPostcode);
}
