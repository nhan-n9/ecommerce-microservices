package com.ecmicro.ecommerce.controller;

import com.ecmicro.ecommerce.dto.request.UserCreateDTO;
import com.ecmicro.ecommerce.dto.request.UserUpdateDTO;
import com.ecmicro.ecommerce.dto.response.UserInfoDTO;
import com.ecmicro.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserInfoDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "username") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Page<UserInfoDTO> users = userService.findAllByPagination(page, size, sortBy, ascending);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> findById(@PathVariable Long id){
        UserInfoDTO user = userService.findById(id);

        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody UserCreateDTO dto){
        Long newUserId = userService.createUser(dto);

        if (newUserId != null) return ResponseEntity.ok(newUserId);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/update")
    public ResponseEntity<UserInfoDTO> updateUser(@RequestBody UserUpdateDTO dto) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return null;
    }
}
