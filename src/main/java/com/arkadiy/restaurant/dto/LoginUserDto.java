package com.arkadiy.restaurant.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message = "Username could not be empty")
    private String username;
    @NotBlank(message = "Password could not be empty")
    private String password;
}
