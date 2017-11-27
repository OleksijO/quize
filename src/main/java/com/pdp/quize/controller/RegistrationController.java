package com.pdp.quize.controller;

import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public RegistrationDto registerUser(@RequestBody RegistrationDto registrationDto, HttpServletResponse response){
        if (Objects.isNull(registrationDto)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        userService.create(registrationDto);
        return registrationDto;
    }

}
