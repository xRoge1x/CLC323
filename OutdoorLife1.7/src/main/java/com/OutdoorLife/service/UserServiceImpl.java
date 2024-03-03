package com.OutdoorLife.service;

import com.OutdoorLife.LogComponent.LoggerInterceptor;
import com.OutdoorLife.entity.UserEntity;
import com.OutdoorLife.repository.UserRepository;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private LoggerInterceptor logger;
    
    @Override
    public boolean authenticateUser(String username, String password) {
        UserEntity user = userRepository.findByUsername(username);
        logger.log(Level.INFO, UserServiceImpl.class.getName(), "authenticateUser", "User authenticated" );
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public void registerUser(String username, String password, String email) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        userRepository.save(newUser);
        System.out.println("User registered: " + username);
        logger.log(Level.INFO, UserServiceImpl.class.getName(), "registerUser", "User registered: " + username );
    }

}
