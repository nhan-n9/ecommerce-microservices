package com.ecmicro.identity.repository;

import com.ecmicro.identity.domain.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends JpaRepository<UserCredentials, Long> {
    UserCredentials findByUsername(String username);
}
