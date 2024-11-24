package com.arkadiy.restaurant.controller;

import com.arkadiy.restaurant.dto.RegisterUserDto;
import com.arkadiy.restaurant.entity.User;
import com.arkadiy.restaurant.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public  class  AdminController {
    private  final UserService userService;

    public  AdminController (UserService userService) {
        this .userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createAdministrator (@Valid @RequestBody RegisterUserDto registerUserDto) {
        User  createdAdmin  = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createdAdmin);
    }
}
