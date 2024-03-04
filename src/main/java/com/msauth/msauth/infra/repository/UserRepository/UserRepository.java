package com.msauth.msauth.infra.repository.UserRepository;

import com.msauth.msauth.domain.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    UserDetails findByemail(String username);


    @Query("SELECT u FROM users u WHERE u.email = :email")
    Optional<Users> findByEmailOptional(@Param("email") String email);


}
