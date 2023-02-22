package com.dtotest.dto.Controller;


import com.dtotest.dto.service.dto.AccountSettingsDTO;
import com.dtotest.dto.service.interfaces.AccountSettingsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/account_settings")
@Data
public class AccountSettingsController {

    private final AccountSettingsService accountSettingsService;

    @GetMapping
    public List<AccountSettingsDTO> getAccountSettings(){
        return accountSettingsService.getAccountSettings();
    }

    @GetMapping(path = "{id}")
    public AccountSettingsDTO getAccountSettingsById(@PathVariable("id") Integer id){
        return accountSettingsService.getAccountSettingsById(id);
    }
    @PutMapping(path = "{id}")
    public void updateAccountSettings(@PathVariable("id") Integer Id,
                              @RequestParam(required = false) String language,
                              @RequestParam(required = false) String theme,
                              @RequestParam(required = false) String timezone) {
        accountSettingsService.updateAccountSettings(Id, language, theme, timezone);
    }
}
