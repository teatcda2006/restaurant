package com.arkadiy.restaurant.bootstrap;

import com.arkadiy.restaurant.dto.RegisterUserDto;
import com.arkadiy.restaurant.entity.Role;
import com.arkadiy.restaurant.entity.enums.ERole;
import com.arkadiy.restaurant.entity.User;
import com.arkadiy.restaurant.repository.RoleRepository;
import com.arkadiy.restaurant.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setUsername("admin");
        userDto.setEmail("admin@email.com");
        userDto.setPassword("admin");

        Optional<Role> optionalRole = roleRepository.findByName(ERole.ADMIN);
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}
