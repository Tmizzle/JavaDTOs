package com.dtotest.dto.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class Project {

    @Id
    @SequenceGenerator(
            name = "new_project_sequence",
            sequenceName = "new_project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "new_project_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private Integer id;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "type",
            nullable = false
    )
    private String type;
    @Column(
            name = "internal_code",
            nullable = false
    )
    private String internalCode;
    @Column(
            name = "start_date",
            nullable = false
    )
    private Date startDate;
    @Column(
            name = "end_date",
            nullable = false
    )
    private Date endDate;
    @Column(
            name = "client_pays_break_time",
            nullable = false
    )
    private boolean clientPaysBreakTime;
}
