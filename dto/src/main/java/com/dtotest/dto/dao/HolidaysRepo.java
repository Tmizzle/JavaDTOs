package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepo extends JpaRepository<Holidays, Integer> {
}
