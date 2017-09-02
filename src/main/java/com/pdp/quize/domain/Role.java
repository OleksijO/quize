package com.pdp.quize.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TUTOR("tutor"), STUDENT("student");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
