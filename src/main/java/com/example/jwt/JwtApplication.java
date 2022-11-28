package com.example.jwt;

import com.example.jwt.User.User;
import com.example.jwt.User.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JwtApplication {


    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

}