package com.krishnasai.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.krishnasai.jobportal.dto.LoginRequestDTO;
import com.krishnasai.jobportal.dto.LoginResponseDTO;
import com.krishnasai.jobportal.dto.UserRequestDTO;
import com.krishnasai.jobportal.dto.UserResponseDTO;
import com.krishnasai.jobportal.entity.Role;
import com.krishnasai.jobportal.entity.User;
import com.krishnasai.jobportal.exception.UserNotFoundException;
import com.krishnasai.jobportal.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO registerUser(UserRequestDTO dto){

        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode((dto.getPassword())))
                .role(Role.valueOf(dto.getRole()))
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .build();
    }


    public LoginResponseDTO login(LoginRequestDTO dto){

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean isMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if(!isMatch){
            throw new RuntimeException("Invaild email or password");
        }

        return LoginResponseDTO.builder()
                .message("Login Successful")
                .email(user.getEmail())
                .build();
    }
}
