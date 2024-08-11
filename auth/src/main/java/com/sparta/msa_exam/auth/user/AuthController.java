package com.sparta.msa_exam.auth.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final String serverPort = "19095";

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto requestDto){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Prot", serverPort);

        return new ResponseEntity<>(authService.signUp(requestDto), headers, HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody UserRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Prot", serverPort);

       return new ResponseEntity<>(authService.createToken(requestDto), headers, HttpStatus.OK);
    }
}
