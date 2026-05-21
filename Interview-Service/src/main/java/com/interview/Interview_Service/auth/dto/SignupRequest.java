package com.interview.Interview_Service.auth.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
}
