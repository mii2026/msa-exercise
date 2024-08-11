package com.sparta.msa_exam.auth.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto requestDto){
        return ResponseEntity.ok(authService.signUp(requestDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody UserRequestDto requestDto) {
       return ResponseEntity.ok(authService.createToken(requestDto));
    }
}
