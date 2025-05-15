package com.jwtworking.dto;

import com.jwtworking.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String name;

    private String email;

    private String password;
    private List<Role> roles = new ArrayList<>();

}
