package com.dtotest.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Holidays {

    @Id
    @SequenceGenerator(
            name = "holiday_sequence",
            sequenceName = "holiday_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "holiday_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
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
            nullable = false
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Column(
            nullable = false
    )
    private String name;

}
