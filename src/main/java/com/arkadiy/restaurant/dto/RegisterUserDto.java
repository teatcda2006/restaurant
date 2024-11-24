package com.arkadiy.restaurant.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank(message = "Username could not be empty")
    @Size(min = 2, max = 255, message = "The length of username must be between 2 and 255 characters")
    private String username;
    @NotBlank(message = "Email could not be empty")
    @Size(min = 10, max = 255, message = "The length of email must be between 2 and 255 characters")
    @Email
    private String email;
    @NotBlank(message = "Password could not be empty")
    @Size(min = 6, max = 3000)
    private String password;
}
