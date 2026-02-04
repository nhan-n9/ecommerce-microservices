package com.ecmicro.identity.service;

import com.ecmicro.identity.domain.UserCredentials;
import com.ecmicro.identity.dto.LoginRequestDTO;
import com.ecmicro.identity.repository.IdentityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentityService {
    private final IdentityRepository identityRepository;

    // init password encoder with strength 10
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    private final AuthenticationManager authManager;

    private final JwtService jwtService;

    public Long saveUserCredentials(UserCredentials cred) {
        // handle password encoding
        String encodedPass = passwordEncoder.encode(cred.getPassword());
        cred.setPassword(encodedPass);

        UserCredentials user = identityRepository.save(cred);

        return user.getId();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

    public String login(LoginRequestDTO request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            return generateToken(request.getUsername());
        }
        return null;
    }
}
