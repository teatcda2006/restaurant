package com.arkadiy.restaurant.controller;

import com.arkadiy.restaurant.entity.User;
import com.arkadiy.restaurant.dto.LoginUserDto;
import com.arkadiy.restaurant.dto.RegisterUserDto;
import com.arkadiy.restaurant.dto.LoginResponse;
import com.arkadiy.restaurant.services.AuthenticationService;
import com.arkadiy.restaurant.services.JwtService;
import com.arkadiy.restaurant.validation.ResponseErrorValidation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final ResponseErrorValidation responseErrorValidation;
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(ResponseErrorValidation responseErrorValidation, JwtService jwtService, AuthenticationService authenticationService) {
        this.responseErrorValidation = responseErrorValidation;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterUserDto registerUserDto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (errors != null && errors.hasBody()) return errors;

        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (errors != null && errors.hasBody()) return errors;

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
