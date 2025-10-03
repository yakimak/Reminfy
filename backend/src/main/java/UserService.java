package com.volunteer.center.services;

import com.volunteer.center.models.User;
import com.volunteer.center.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User registerUser(String email, String password, String fullName) {
        // Проверяем, нет ли уже пользователя с таким email
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User with this email already exists");
        }
        
        // Создаем нового пользователя
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Шифруем пароль
        user.setFullName(fullName);
        
        // Сохраняем в базу
        return userRepository.save(user);
    }
    
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}