package com.example.jwt.User;

import com.example.jwt.payLoad.LoginRequest;
public interface UserServiceAuth {
    public User create(LoginRequest loginRequest);
}
