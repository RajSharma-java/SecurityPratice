package com.jwtworking.controller;

import com.jwtworking.Util.JwtHelper;
import com.jwtworking.dto.JwtRequest;
import com.jwtworking.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtRequest.getEmail(),
                        jwtRequest.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = jwtHelper.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
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
