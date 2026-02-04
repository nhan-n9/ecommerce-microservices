package com.ecmicro.identity.controller;

import com.ecmicro.identity.domain.UserCredentials;
import com.ecmicro.identity.dto.LoginRequestDTO;
import com.ecmicro.identity.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/identity")
@RequiredArgsConstructor
public class IdentityController {
    private final IdentityService identityService;

    @PostMapping("/register")
    public ResponseEntity<Long> createUser(@RequestBody UserCredentials cred) {
        Long newId = identityService.saveUserCredentials(cred);

        if (newId != null) return ResponseEntity.ok(newId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/token")
    public ResponseEntity<String> createToken(@RequestBody String username) {
        String token = identityService.generateToken(username);

        if (token != null) return ResponseEntity.ok(token);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<String> validateToken(@PathVariable String token) {
        identityService.validateToken(token);

        return ResponseEntity.ok("Validated!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO request) {
        String token = identityService.login(request);
        if (token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("Token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
