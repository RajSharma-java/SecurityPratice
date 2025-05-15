package com.jwtworking.controller;

import com.jwtworking.config.CustomUserDetail;
import com.jwtworking.dto.LoginDto;
import com.jwtworking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Login successful");
    }

    // get the userName

    @GetMapping("username")
    public ResponseEntity<String > getUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // that give email
        String role = auth.getAuthorities().toString();
        return ResponseEntity.ok("UserName  :"+name+ " Role is :"+role);
    }
}
