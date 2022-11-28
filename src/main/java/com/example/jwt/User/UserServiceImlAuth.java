package com.example.jwt.User;

import com.example.jwt.payLoad.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImlAuth implements UserServiceAuth{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImlAuth(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(LoginRequest loginRequest) {
        User user=new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

        userRepository.save(user);
        return user;
    }

}
