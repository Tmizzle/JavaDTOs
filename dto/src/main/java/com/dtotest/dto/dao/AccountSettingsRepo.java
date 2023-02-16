package com.dtotest.dto.dao;

import com.dtotest.dto.entity.AccountSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSettingsRepo extends JpaRepository<AccountSettings, Integer> {
}
