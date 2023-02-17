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

}
