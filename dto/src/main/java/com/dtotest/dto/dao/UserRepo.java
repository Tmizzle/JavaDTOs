package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {
}
