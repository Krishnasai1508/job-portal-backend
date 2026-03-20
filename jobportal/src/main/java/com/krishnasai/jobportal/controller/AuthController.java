package com.krishnasai.jobportal.controller;

import com.krishnasai.jobportal.dto.LoginRequestDTO;
import com.krishnasai.jobportal.dto.LoginResponseDTO;
import com.krishnasai.jobportal.dto.UserRequestDTO;
import com.krishnasai.jobportal.dto.UserResponseDTO;
import com.krishnasai.jobportal.entity.User;
import com.krishnasai.jobportal.repository.UserRepository;
import com.krishnasai.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO registerUser(@RequestBody UserRequestDTO dto){

        return userService.registerUser(dto);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto){
        return userService.login(dto);
    }
}
