package com.pdp.quize.controller;

import com.pdp.quize.controller.dto.validation.RegistrationDtoValidator;
import com.pdp.quize.domain.dto.RegistrationDto;
import com.pdp.quize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationDtoValidator registrationDtoValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(registrationDtoValidator);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object registerUser(@Valid @RequestBody RegistrationDto registrationDto,
                               HttpServletResponse response) {
        if (Objects.isNull(registrationDto)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        userService.create(registrationDto);
        return registrationDto;
    }
}
