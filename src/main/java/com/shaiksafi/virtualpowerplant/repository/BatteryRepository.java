package com.shaiksafi.virtualpowerplant.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shaiksafi.virtualpowerplant.model.Battery;

@Repository
public interface BatteryRepository extends CrudRepository<Battery, Long> {

	List<Battery> findByPostcodeBetween(String start, String end);
}
