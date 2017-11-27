package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.LoginDto;
import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.service.UserService;
import com.pdp.quize.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public RegistrationDto loginUser(@RequestBody LoginDto loginDto, HttpServletResponse response){
        if (Objects.isNull(loginDto)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        loginDto.setPassword(PasswordUtils.encrypt(loginDto.getPassword()));
        return userService.login(loginDto);
    }

}
