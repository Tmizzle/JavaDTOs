package com.dtotest.dto.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
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

}
