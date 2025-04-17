package com.example.springbootmongodb.service;

import com.example.springbootmongodb.model.User;
import com.example.springbootmongodb.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JWTService jwtService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public String  verifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } 
        throw new RuntimeException("Invalid username or password");
    }




    // Add a test user on application startup
    @PostConstruct
    public void init() {
        if (userRepository.findByUsername("kiran") == null) {
            User user = new User();
            user.setUsername("kiran");
            user.setPassword("k@123");
            userRepository.save(user);
            System.out.println("Test user created: " + user.getUsername());
        }
    }
} 