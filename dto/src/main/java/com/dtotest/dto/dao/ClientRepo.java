package com.dtotest.dto.dao;

import com.dtotest.dto.entity.Client;
import com.dtotest.dto.entity.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE c.internalCode= ?1")
    Optional<Client> findClientByInternalCode(String code);

}
