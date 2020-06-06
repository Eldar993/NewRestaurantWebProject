package com.example.restaurant.dto;

import com.example.restaurant.enums.UserRoles;

public class UserDto {
    private Long id;
    private String name;
    private String password;
    private UserRoles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getUserRole() {
        return role;
    }

    public void setUserRole(UserRoles role) {
        this.role = role;
    }
}
