package com.jwtworking.service;

import com.jwtworking.dto.UserDto;
import com.jwtworking.model.Role;
import com.jwtworking.model.User;
import com.jwtworking.repository.RoleRepository;
import com.jwtworking.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    // create
    public UserDto create(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ROLE_USER");
                    return roleRepository.save(newRole);
                });

        // Assign the role to the user
        user.setRoles(List.of(userRole));
        User save = userRepository.save(user);
        UserDto map = mapper.map(save, UserDto.class);
        return map;
    }

    // getAll
    public List<User> getAll(){
       return userRepository.findAll();

    }

    //getById
    public UserDto getById(Long id){
        Optional<User> byId = userRepository.findById(id);
        return mapper.map(byId, UserDto.class);
    }
}
