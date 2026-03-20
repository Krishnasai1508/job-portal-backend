package com.krishnasai.jobportal.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private String role;
}
