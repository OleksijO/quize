package com.pdp.quize.util;

public class PasswordUtils {

    public static String encrypt(String password){
        return Integer.toHexString(password.hashCode());
    }

}
