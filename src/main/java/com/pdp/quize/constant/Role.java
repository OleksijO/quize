package com.pdp.quize.constant;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;

public enum Role implements GrantedAuthority {
    TEACHER, STUDENT;

    @Override
    public String getAuthority() {
        return this.name();
    }

    public static Role of(String value){
        return Arrays.stream(values())
                .filter(role->role.toString()
                        .equalsIgnoreCase(value))
                .findAny()
                .orElse(null);
    }
}
