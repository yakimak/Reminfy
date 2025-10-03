package com.volunteer.center.controllers;

import com.volunteer.center.dto.RegisterRequest;
import com.volunteer.center.models.User;
import com.volunteer.center.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Для Angular разработки
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            User user = userService.registerUser(
                registerRequest.getEmail(),
                registerRequest.getPassword(), 
                registerRequest.getFullName()
            );
            
            // Возвращаем успешный ответ (пока без JWT токена)
            return ResponseEntity.ok().body("User registered successfully");
            
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}