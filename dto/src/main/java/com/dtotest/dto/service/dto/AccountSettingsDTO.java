package com.dtotest.dto.service.dto;

import com.dtotest.dto.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
public class AccountSettingsDTO{
        private String language;
        private String theme;
        private String timezone;
        private CountryDTO country;
}
