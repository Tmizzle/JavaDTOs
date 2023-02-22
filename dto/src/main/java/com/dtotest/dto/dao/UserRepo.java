package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.email= ?1")
    Optional<Users> findUserByEmail(String email);

    @Query("SELECT u FROM Users u WHERE u.username= ?1")
    Optional<Users> findUserByUsername(String username);
}
