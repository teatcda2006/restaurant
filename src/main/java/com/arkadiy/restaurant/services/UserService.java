package com.arkadiy.restaurant.services;

import com.arkadiy.restaurant.dto.RegisterUserDto;
import com.arkadiy.restaurant.entity.Role;
import com.arkadiy.restaurant.entity.User;
import com.arkadiy.restaurant.entity.enums.ERole;
import com.arkadiy.restaurant.repository.RoleRepository;
import com.arkadiy.restaurant.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private  final UserRepository userRepository;
    private  final RoleRepository roleRepository;

    private  final PasswordEncoder passwordEncoder;

    public  UserService (UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User createAdministrator (RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(ERole.ADMIN);

        if (optionalRole.isEmpty()) {
            return  null ;
        }

        var  user  =  new  User ();
        user.setUsername(input.getUsername());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
}