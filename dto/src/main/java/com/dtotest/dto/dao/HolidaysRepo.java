package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Holidays;
import com.dtotest.dto.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HolidaysRepo extends JpaRepository<Holidays, Integer> {

    @Query("SELECT h FROM Holidays h WHERE h.country.name= ?1")
    List<Holidays> findHolidayByCountry(String country);
}
