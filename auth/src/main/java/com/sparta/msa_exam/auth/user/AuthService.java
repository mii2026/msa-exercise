package com.sparta.msa_exam.auth.user;

public interface AuthService {
    UserResponseDto signUp(UserRequestDto requestDto);
    String createToken(UserRequestDto requestDto);
}
