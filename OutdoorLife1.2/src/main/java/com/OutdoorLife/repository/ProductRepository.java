package com.OutdoorLife.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.OutdoorLife.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {

    ProductEntity findByProductName(String productName);

    List<ProductEntity> findByPriceLessThan(int price);

    List<ProductEntity> findAll();
}
