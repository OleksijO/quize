package com.pdp.quize.controller.dto.validation;

import com.pdp.quize.constant.Role;
import com.pdp.quize.domain.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.PostConstruct;
import java.util.regex.Pattern;

@Component
public class RegistrationDtoValidator implements Validator {
    private Pattern emailPattern;
    private Pattern passwordPattern;
    private Pattern namePattern;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        emailPattern = Pattern.compile(env.getProperty("validation.regexp.email"));
        passwordPattern = Pattern.compile(env.getProperty("validation.regexp.password"));
        namePattern = Pattern.compile(env.getProperty("validation.regexp.name"));
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationDto dto = (RegistrationDto) target;
        checkEmail(dto, errors);
        checkFirstName(dto, errors);
        checkLastName(dto, errors);
        checkPassword(dto, errors);
        checkRole(dto, errors);
    }

    private void checkEmail(RegistrationDto dto, Errors errors) {
        if (!emailPattern.matcher(dto.getEmail()).matches()) {
            errors.rejectValue("Email", "Email should be valid!");
        }
    }

    private void checkFirstName(RegistrationDto dto, Errors errors) {
        if (!namePattern.matcher(dto.getFirstName()).matches()) {
            errors.rejectValue("FirstName", "First name should contain only letters and space. Length: 1-20");
        }
    }

    private void checkLastName(RegistrationDto dto, Errors errors) {
        if (!namePattern.matcher(dto.getLastName()).matches()) {
            errors.rejectValue("LastName", "Last name should contain only letters and space. Length: 1-20");
        }
    }

    private void checkPassword(RegistrationDto dto, Errors errors) {
        if (!passwordPattern.matcher(dto.getPassword()).matches()) {
            errors.rejectValue("Password", "Password should contain only letters, numbers, '_' and '-'. Length: 4-12");
        }
    }

    private void checkRole(RegistrationDto dto, Errors errors) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(dto.getRole())) {
                return;
            }
        }
        errors.rejectValue("Role", "Could not grant specified authority");


    }
}
