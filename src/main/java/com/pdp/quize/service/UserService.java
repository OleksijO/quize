package com.pdp.quize.service;

import com.pdp.quize.constant.Role;
import com.pdp.quize.domain.User;
import com.pdp.quize.domain.dto.LoginDto;
import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.repository.UserRepository;
import com.pdp.quize.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(RegistrationDto registrationData){
        User user = new User();
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setEmail(registrationData.getEmail());
        user.setPassword(PasswordUtils.encrypt(registrationData.getPassword()));
        user.setRole(Role.of(registrationData.getRole()));

        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User with specified email already exists");
        }
        userRepository.save(user);
    }

    public RegistrationDto login(LoginDto loginDto) {
        User user = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        RegistrationDto dto = new RegistrationDto();
        if (user == null) {
            dto.setRole("");
            return dto;
        }

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRole(user.getRole().getAuthority());
        return dto;
    }

}

