package com.example.jwt.Controller;

import javax.validation.Valid;
import com.example.jwt.User.CustomUserDetails;
import com.example.jwt.User.User;
import com.example.jwt.User.UserServiceAuth;
import com.example.jwt.jwt.JwtTokenProvider;
import com.example.jwt.payLoad.LoginRequest;
import com.example.jwt.payLoad.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WebController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceAuth userServiceAuth;
    private final JwtTokenProvider tokenProvider;

    public WebController(AuthenticationManager authenticationManager, UserServiceAuth userServiceAuth, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userServiceAuth = userServiceAuth;
        this.tokenProvider = tokenProvider;
    }
    @GetMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // xác thực thông tin người dùng Request lên
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        // set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    // trả về hello
    @GetMapping("/hello")
    public String hello(){
       return "hello";
    }

    // tạo tài khoản mới
    @GetMapping("/create")
    public User create(@RequestBody LoginRequest loginRequest){
        return userServiceAuth.create(loginRequest);
    }

}