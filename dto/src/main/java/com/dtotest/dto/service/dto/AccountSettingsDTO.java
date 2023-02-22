package com.dtotest.dto.service.dto;

import lombok.Data;

@Data
public class AccountSettingsDTO{
        private String language;
        private String theme;
        private String timezone;
        private CountryDTO country;
}
