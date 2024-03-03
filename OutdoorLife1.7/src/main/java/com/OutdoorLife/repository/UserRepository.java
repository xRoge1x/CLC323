package com.OutdoorLife.repository;

import org.springframework.data.repository.CrudRepository;

import com.OutdoorLife.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}