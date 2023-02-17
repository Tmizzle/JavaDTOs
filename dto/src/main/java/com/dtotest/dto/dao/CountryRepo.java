package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Integer> {
}
