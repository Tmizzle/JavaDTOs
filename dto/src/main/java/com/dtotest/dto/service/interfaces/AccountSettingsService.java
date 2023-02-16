package com.dtotest.dto.service.interfaces;

import com.dtotest.dto.dao.AccountSettingsRepo;
import com.dtotest.dto.entity.AccountSettings;
import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.mapper.AccountSettingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountSettingsService {

    private final AccountSettingsRepo accountSettingsRepo;
    private final AccountSettingsMapper accountSettingsMapper;


    @Autowired
    public AccountSettingsService(AccountSettingsRepo accountSettingsRepo, AccountSettingsMapper accountSettingsMapper) {
        this.accountSettingsRepo = accountSettingsRepo;
        this.accountSettingsMapper = accountSettingsMapper;
    }

    public List<AccountSettingsDTO> getAccountSettings() {
        return accountSettingsRepo.findAll()
                .stream()
                .map(accountSettingsMapper)
                .collect(Collectors.toList());
    }

    public List<AccountSettingsDTO> GetAccountSettingsById(Integer id){
        return accountSettingsRepo.findById(id)
                .stream()
                .map(accountSettingsMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAccountSettings(Integer Id, String language, String theme, String timezone) {
        AccountSettings accountSettings = accountSettingsRepo.findById(Id).orElseThrow(() -> new IllegalStateException("" +
                "account setting with id " + Id + " does not exist"));

        if (language != null && language.length() > 0 && !Objects.equals(accountSettings.getLanguage(), language)) {
            accountSettings.setLanguage(language);
        }

        if (theme != null && theme.length() > 0 && !Objects.equals(accountSettings.getTheme(), theme)) {
            accountSettings.setTheme(theme);
        }

        if (timezone != null && timezone.length() > 0 && !Objects.equals(accountSettings.getTimezone(), timezone)) {
            accountSettings.setTimezone(timezone);
        }
    }
}
