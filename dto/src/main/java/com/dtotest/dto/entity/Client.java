package com.dtotest.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Client {

    @Id
    @SequenceGenerator(
            name = "new_client_sequence",
            sequenceName = "new_client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "new_client_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private Integer id;
    @Column(
            nullable = false
    )
    private String name;
    @Column(
            name = "internal_code",
            unique = true,
            nullable = false
    )
    private String internalCode;
    @Column(
            nullable = false
    )
    private String industry;
    @Column(
            nullable = false
    )
    private String country;
    @Column(
            nullable = false
    )
    private String city;
    @Column(
            name = "street_name",
            nullable = false
    )
    private String streetName;
    @Column(
            name = "street_number",
            nullable = false
    )
    private String streetNumber;
    @Column(
            nullable = false
    )
    private String zip;
}
