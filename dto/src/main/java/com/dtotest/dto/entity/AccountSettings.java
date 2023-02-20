package com.dtotest.dto.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class AccountSettings {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column(
            updatable = false
    )
    private Integer id;

    @OneToOne(targetEntity = Country.class, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_country",
            referencedColumnName = "id",
            nullable = false
    )
    private Country country;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String timezone;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String language;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String theme;

    public AccountSettings(Country defaultCountry) {
        this.theme = "Light";
        this.language = "Hrvatski";
        this.timezone = "Europe/Zagreb";
        this.country = defaultCountry; // Set the default country
    }

}
