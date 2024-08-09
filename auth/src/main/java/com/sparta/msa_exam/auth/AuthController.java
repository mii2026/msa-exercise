package com.sparta.msa_exam.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final String serverPort = "19095";

    @GetMapping("/auth/signIn")
    public ResponseEntity<?> signIn(@RequestParam("user_id") String userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Prot", serverPort);

       return new ResponseEntity<>(authService.createToken(userId, "ADMIN"), headers, HttpStatus.OK);
    }
}
