package com.cvrs.backend.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptUtil {
    public static String bCrypt(String data){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.encode(data);
    }

    public static Boolean match(String raw,String encrypted){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        return passwordEncoder.matches(raw,encrypted);
    }
}
