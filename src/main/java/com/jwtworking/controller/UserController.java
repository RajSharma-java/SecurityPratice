package com.jwtworking.controller;

import com.jwtworking.dto.UserDto;
import com.jwtworking.model.User;
import com.jwtworking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    @Autowired
    private UserService userService;

    // create
    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        UserDto userDto1 = userService.create(userDto);
        return ResponseEntity.ok(userDto1);
    }

    // get all
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll(){

        List<User> all = userService.getAll();
        return ResponseEntity.ok(all);
    }

    // getById
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id){
        UserDto byId = userService.getById(id);
        return ResponseEntity.ok(byId);
    }

}
