package com.OutdoorLife.repository;

import org.springframework.data.repository.CrudRepository;

import com.OutdoorLife.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {
	
}