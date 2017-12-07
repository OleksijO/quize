package com.pdp.quize.service;

import com.pdp.quize.constant.Role;
import com.pdp.quize.domain.User;
import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(RegistrationDto registrationData) {
        User user = new User();
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setEmail(registrationData.getEmail());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setRole(Role.of(registrationData.getRole()));

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with specified email already exists");
        }
        userRepository.save(user);
    }
}

