package com.sparta.msa_exam.auth.user;

import lombok.Data;

@Data
public class UserRequestDto {
    private String username;
    private String password;
}
