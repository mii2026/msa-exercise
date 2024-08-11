package com.sparta.msa_exam.auth.user;

import com.sparta.msa_exam.auth.core.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String issuer;
    private final Long accessExpiration;
    private final SecretKey secretKey;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Value("${spring.application.name}") String issuer,
            @Value("${service.jwt.access-expiration}") Long accessExpiration,
            @Value("${service.jwt.secret-key}") String secretKey
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.issuer = issuer;
        this.accessExpiration = accessExpiration;
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    @Override
    public UserResponseDto signUp(UserRequestDto requestDto) {
        User user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role("MEMBER")
                .build();

        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    public String createToken(UserRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        long now = System.currentTimeMillis();
        return Jwts.builder()
                .claim("user_id", user.getUserId())
                .claim("role", user.getRole())
                .issuer(issuer)
                .issuedAt(new Date(now))
                .expiration(new Date(now + accessExpiration))
                .signWith(secretKey)
                .compact();
    }
}
