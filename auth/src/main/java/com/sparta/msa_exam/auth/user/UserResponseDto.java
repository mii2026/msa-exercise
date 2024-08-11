package com.sparta.msa_exam.auth.user;

import com.sparta.msa_exam.auth.core.domain.User;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long user_id;
    private String username;
    private String role;

    public UserResponseDto(User user) {
        this.user_id = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
