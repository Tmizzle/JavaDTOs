package com.dtotest.dto.service.dto;

import com.dtotest.dto.entity.Country;
import lombok.Data;
import org.mapstruct.Mapping;

@Data
public class AccountSettingsDTO{
        private String language;
        private String theme;
        private String timezone;
        private Country country;
}
