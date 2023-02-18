package com.dtotest.dto.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            updatable = false,
            nullable = false
    )
    private Integer id;
    @OneToOne(targetEntity = AccountSettings.class, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_account_settings",
            referencedColumnName = "id",
            nullable = false
    )
    private AccountSettings accountSettings;
    private String username;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "middle_name",
            nullable = false
    )
    private String middleName;
    private String gender;
    @Column(
            name = "birth_date",
            nullable = false
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String password;
    @Column(
            name = "old_password",
            nullable = false
    )
    private String oldPassword;
    private String permission;
    @Column(
            name = "started_working",
            nullable = false
    )
    private Date startedWorking;
    private Integer experience;
    private boolean enabled;
    private boolean active;
    @Column(
            name = "deleted_globally"
    )
    private boolean deletedGlobally;
    @Column(
            name = "deleted_in_organization"
    )
    private boolean deletedInOrganization;
    private boolean verified;
    private String seniority;
    @Column(
            name = "user_category"
    )
    private String userCategory;
    @Column(
            name = "updated_at"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    @Column(
            name = "created_at"
    )
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

}
