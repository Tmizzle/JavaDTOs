package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepo extends JpaRepository<Country, Integer> {

    @Query("SELECT id FROM Country c WHERE c.name= ?1")
    Integer findCountry(String country);
}
