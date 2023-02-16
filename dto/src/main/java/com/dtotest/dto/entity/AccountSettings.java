package com.dtotest.dto.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table
public class AccountSettings {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private Integer id;

    private Integer id_country;

    private String timezone;

    private String language;

    private String theme;

    public AccountSettings() {
    }

    public AccountSettings(Integer id, Integer id_country, String timezone, String language, String theme) {
        this.id = id;
        this.id_country = id_country;
        this.timezone = timezone;
        this.language = language;
        this.theme = theme;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_country() {
        return id_country;
    }

    public void setId_country(Integer id_country) {
        this.id_country = id_country;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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

    @Override
    public String toString() {
        return "AccountSettings{" +
                "id=" + id +
                ", id_country=" + id_country +
                ", timezone='" + timezone + '\'' +
                ", language='" + language + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }
}
