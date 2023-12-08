package com.shaiksafi.virtualpowerplant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shaiksafi.virtualpowerplant.dto.BatteryDTO;
import com.shaiksafi.virtualpowerplant.entity.Battery;

//Repository interface for CURD operations

@Repository
public interface BatteryRepository extends CrudRepository<Battery, Long> {
	
	//Finds batteries within given range
	List<Battery> findByPostcodeBetween(int startPostcode, int endPostcode);
}
