package com.ecmicro.identity.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_credentials")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;
}
