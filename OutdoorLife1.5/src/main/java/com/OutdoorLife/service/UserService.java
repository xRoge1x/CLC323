package com.OutdoorLife.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	public boolean authenticateUser(String username, String password);
	
    public void registerUser(String username, String password, String email);
}
