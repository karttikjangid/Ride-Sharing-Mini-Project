package com.choudharykhushboo.RideShare.controller;

//import org.springframework.stereotype.Controller;
import com.choudharykhushboo.RideShare.dto.AuthResponse;
import com.choudharykhushboo.RideShare.dto.LoginRequest;
import com.choudharykhushboo.RideShare.dto.RegisterRequest;
import com.choudharykhushboo.RideShare.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest req){
        authService.register(req);
       return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest req){
      AuthResponse res = authService.login(req);
        return ResponseEntity.ok(res);
    }
}
