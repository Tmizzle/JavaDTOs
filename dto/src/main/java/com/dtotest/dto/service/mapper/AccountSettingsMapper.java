package com.dtotest.dto.service.mapper;

import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountSettingsMapper implements Function<AccountSettings, AccountSettingsDTO> {
    @Override
    public AccountSettingsDTO apply(AccountSettings accountSettings) {
        return new AccountSettingsDTO(
                accountSettings.getId(),
                accountSettings.getLanguage(),
                accountSettings.getTheme(),
                accountSettings.getTimezone()
        );
    }
}