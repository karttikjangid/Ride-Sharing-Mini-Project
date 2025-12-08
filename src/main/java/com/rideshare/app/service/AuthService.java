package com.rideshare.app.service;

import com.rideshare.app.dto.AuthResponse;
import com.rideshare.app.dto.LoginRequest;
import com.rideshare.app.dto.RegisterRequest;
import com.rideshare.app.model.User;
import com.rideshare.app.repository.UserRepository;
import com.rideshare.app.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    public void register(RegisterRequest req){
        if(userRepository.findByUsername(req.getUsername()).isPresent()){
            throw new RuntimeException("Username already taken");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        userRepository.save(user);
    }
    public AuthResponse login(LoginRequest req){
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token,user.getRole());
    }
}
