package com.dtotest.dto.service.dto;

import lombok.Data;

@Data
public class AccountSettingsDTO{
        private Integer id;
        private String language;
        private String theme;
        private String timezone;
}
