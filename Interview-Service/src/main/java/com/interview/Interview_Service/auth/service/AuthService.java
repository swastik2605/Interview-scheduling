package com.interview.Interview_Service.auth.service;

import com.interview.Interview_Service.auth.dto.LoginRequest;
import com.interview.Interview_Service.auth.dto.SignupRequest;
import com.interview.Interview_Service.auth.model.User;
import com.interview.Interview_Service.auth.repository.UserRepo;
import com.interview.Interview_Service.auth.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String signup(SignupRequest Request){
        User user = new User();
        user.setUsername(Request.getUsername());
        user.setPassword(passwordEncoder.encode(Request.getPassword()));
        user.setRole("USER");
        userRepo.save(user);
        return "User Registered";
    }

    public String login(LoginRequest Request){
        User user = userRepo.findByUsername(Request.getUsername()).orElseThrow(() ->
                new RuntimeException("User Not Found")
        );


        boolean matches = passwordEncoder.matches(
                Request.getPassword(),
                user.getPassword());
        if (!matches) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtService.generateToken(user.getUsername());
    }

}
