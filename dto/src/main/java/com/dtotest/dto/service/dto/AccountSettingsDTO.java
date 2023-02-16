package com.dtotest.dto.service.dto;

import lombok.Data;

public class AccountSettingsDTO{
        private Integer id;
        private String language;
        private String theme;
        private String timezone;

        public AccountSettingsDTO() {
        }

        public AccountSettingsDTO(Integer id, String language, String theme, String timezone) {
                this.id = id;
                this.language = language;
                this.theme = theme;
                this.timezone = timezone;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }

        public String getTheme() {
                return theme;
        }

        public void setTheme(String theme) {
                this.theme = theme;
        }

        public String getTimezone() {
                return timezone;
        }

        public void setTimezone(String timezone) {
                this.timezone = timezone;
        }

        @Override
        public String toString() {
                return "AccountSettingsDTO{" +
                        "id=" + id +
                        ", language='" + language + '\'' +
                        ", theme='" + theme + '\'' +
                        ", timezone='" + timezone + '\'' +
                        '}';
        }
}
