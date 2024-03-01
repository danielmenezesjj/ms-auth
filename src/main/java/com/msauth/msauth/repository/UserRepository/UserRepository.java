package com.msauth.msauth.repository.UserRepository;

import com.msauth.msauth.domain.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, Integer> {
    UserDetails findByEmail(String username);
}
