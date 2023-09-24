package com.scaler.splitwise.strategies;

public class BCryptPasswordHashingStrategy {
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordHashingStrategy(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean match(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }
}
